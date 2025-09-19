package ao.sudojed.backend.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reductio.Reduction;

import reactor.core.publisher.Mono;

@Component
public class FunctionHandler {

    public void q(){
        Reduction r = new Reduction();
        
    }
}