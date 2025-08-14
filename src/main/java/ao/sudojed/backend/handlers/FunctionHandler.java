package ao.sudojed.backend.handlers;

import ao.sudojed.backend.resources.funcao.polinomial.Afim;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FunctionHandler {

    public Mono<ServerResponse> getEstudoCompleto(ServerRequest req) {
        try {
            String expr = req.pathVariable("expr");
            Afim funcao = new Afim(expr);
            Map<String, Object> estudo = funcao.getEstudoCompleto();
            return ServerResponse.ok().bodyValue(estudo);
        } catch (Exception e) {
            return ServerResponse.badRequest().bodyValue(
                "Erro: " + e.getMessage()
            );
        }
        return ServerResponse.ok().bodyValue("Function handler");
    }
}
