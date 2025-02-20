package origami_flow.salgado_trancas_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CepNaoEncontradoException extends RuntimeException {

    public CepNaoEncontradoException() {
        super();
    }

    public CepNaoEncontradoException(String message) {
        super(message);
    }

}
