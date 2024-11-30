package origami_flow.salgado_trancas_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CaixaNaoAbertoException extends RuntimeException {

    public CaixaNaoAbertoException() { super();}

    public CaixaNaoAbertoException(String message) {
        super(message);
    }

    public HttpStatus getStatus() { return this.getStatus();}
}
