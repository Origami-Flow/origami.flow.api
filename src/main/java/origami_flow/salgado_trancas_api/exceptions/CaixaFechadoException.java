package origami_flow.salgado_trancas_api.exceptions;

import org.springframework.http.HttpStatus;

public class CaixaFechadoException extends RuntimeException {

    public CaixaFechadoException() { super();}

    public CaixaFechadoException(String message) {
        super(message);
    }

    public HttpStatus getStatus() { return this.getStatus();}
}
