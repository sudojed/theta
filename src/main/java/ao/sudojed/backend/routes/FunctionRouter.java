package ao.sudojed.backend.routes;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import ao.sudojed.backend.handlers.FunctionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunctionRouter {

    @Bean
    RouterFunction<ServerResponse> routes(FunctionHandler handler) {
        return route().GET("/function", handler::listar).build();
    }
}