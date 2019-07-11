package com.moses.boot.sample.webflux;

import com.moses.boot.persistence.repository.UserJdbcRepository;
import com.moses.boot.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final UserJdbcRepository userJdbcRepository;

    @Autowired
    public UserHandler(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        System.out.printf("[Thread : %s] Flux UserHandler save: \n", Thread.currentThread().getName());
        //在Spring Web MVC中使用@RequestBody
        //在Flux中使用ServerRequest
        //Mono<User> 类似于 Optional<User>
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        //map相当于转换工作
        Mono<Boolean> booleanMono = userMono.map(userJdbcRepository::save);
        return ServerResponse.ok().body(booleanMono, Boolean.class);
    }
}
