package com.moses.boot.sample.config;

import com.moses.boot.sample.model.User;
import com.moses.boot.sample.repository.UserRepository;
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
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionUsers(UserRepository userRepository){
        Collection<User> users = userRepository.getAll();
        Flux<User> userFlux = Flux.fromIterable(users);

        return RouterFunctions.route(RequestPredicates.path("/users"),
                serverRequest -> ServerResponse.ok().body(userFlux, User.class));
    }
}
