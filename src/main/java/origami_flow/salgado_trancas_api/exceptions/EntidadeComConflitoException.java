package origami_flow.salgado_trancas_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeComConflitoException extends RuntimeException {

    public EntidadeComConflitoException() {
        super();
    }

    public EntidadeComConflitoException(String message) {
        super(String.format("Este %s já existe" , message));
    }
}
