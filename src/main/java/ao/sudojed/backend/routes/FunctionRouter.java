package ao.sudojed.backend.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

import ao.sudojed.backend.handlers.FunctionHandler;

@Configuration
public class FunctionRouter{
    
    @Bean
    RouterFunction<ServerResponse> routes (FunctionHandler handler){
        return route()
            .GET("/function", handler::listar)
            .build();
    }
}