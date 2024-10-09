package gm.inventarios.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursosNoEncontrado extends RuntimeException{
    public RecursosNoEncontrado(String mensaje){
        super(mensaje);
    }
}
