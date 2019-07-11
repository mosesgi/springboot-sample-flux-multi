package com.moses.boot.persistence.repository;

import com.moses.boot.sample.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMemoryRepository {
    private static ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();
    private static AtomicInteger idGenerator = new AtomicInteger();

    public boolean saveUser(User user) {
        int id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    public Collection<User> getAll() {
        return repository.values();
    }
}
