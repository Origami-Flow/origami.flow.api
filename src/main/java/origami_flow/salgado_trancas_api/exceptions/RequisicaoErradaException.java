package origami_flow.salgado_trancas_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequisicaoErradaException extends RuntimeException{
    public RequisicaoErradaException() {super();}
    public RequisicaoErradaException(String message) {super(message);}

    public HttpStatus getStatus() { return HttpStatus.BAD_REQUEST;}
}
