package com.moses.boot.persistence.repository;

import com.moses.boot.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;
import java.util.Collections;

@Repository
public class UserJdbcRepository {
    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public UserJdbcRepository(DataSource dataSource,
                              @Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate,
                              PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;		//@Primary datasource
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    private boolean jdbcSave(User user) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
//			connection.setAutoCommit(false);

            //-----------------保护点概念---------------------
            Savepoint savepoint = connection.setSavepoint("t1");
            try {
                transactionalSave(user);
            } catch(Exception e) {
                connection.rollback(savepoint);
            }
            connection.commit();
            connection.releaseSavepoint(savepoint);
            //------------------保护点概念----------------------

            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO user(name) values (?);");
            prepareStatement.setString(1, user.getName());
            success = prepareStatement.executeUpdate() > 0;
//			connection.commit();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Transactional
    public boolean transactionalSave(User user) {
        boolean success = false;
        success = jdbcTemplate.execute("INSERT INTO user(name) values (?);", (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, user.getName());
            return ps.executeUpdate()>0;
        });
        return success;
    }

    @Transactional
    public boolean save(User user) {
        System.out.printf("[Thread : %s] save user: %s\n", Thread.currentThread().getName(), user);

        boolean success = false;
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        success = jdbcTemplate.execute("INSERT INTO user(name) values (?);", new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getName());
                return ps.executeUpdate()>0;
            }

        });

        platformTransactionManager.commit(transactionStatus);
        return success;
    }

    public Collection<User> findAll(){
        return Collections.emptyList();
    }
}
