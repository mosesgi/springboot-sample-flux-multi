package com.moses.boot.sample.webflux;

import com.moses.boot.sample.model.User;
import com.moses.boot.persistence.repository.UserMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler){
        return RouterFunctions.route(RequestPredicates.POST("/jdbc/user/save"), userHandler::save);
    }

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionUsers(UserMemoryRepository userMemoryRepository){
        Collection<User> users = userMemoryRepository.getAll();
        Flux<User> userFlux = Flux.fromIterable(users);

        return RouterFunctions.route(RequestPredicates.path("/memory/users"),
                serverRequest -> ServerResponse.ok().body(userFlux, User.class));
    }
}
