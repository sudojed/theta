package ao.sudojed.backend.handlers;

import ao.sudojed.backend.recursos.funcao.polinomial.Afim;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FunctionHandler {

    public Mono<ServerResponse> listar(ServerRequest req) {
        String expr = req.queryParam("expression").orElseThrow();
        Afim afim = new Afim(expr);
        return ServerResponse.ok().bodyValue(afim.getEstudoCompleto());
    }
}
