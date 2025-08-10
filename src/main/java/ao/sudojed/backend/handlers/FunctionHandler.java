package ao.sudojed.backend.handlers;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FunctionHandler {

    public Mono<ServerResponse> listar(ServerRequest req) {
        return ServerResponse.ok().bodyValue(List.of("Produto 1", "Produto 2"));
    }
}
