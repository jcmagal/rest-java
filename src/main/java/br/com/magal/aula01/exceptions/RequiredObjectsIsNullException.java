package br.com.magal.aula01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectsIsNullException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RequiredObjectsIsNullException(String ex){
        super(ex);
    }

    public RequiredObjectsIsNullException(){
        super("It is not allowed to persist a null object!");
    }
}
