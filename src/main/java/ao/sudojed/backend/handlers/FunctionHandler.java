package ao.sudojed.backend.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class FunctionHandler {

    public Mono<ServerResponse> listar(ServerRequest req) {
        return ServerResponse.ok().bodyValue();
    }
}