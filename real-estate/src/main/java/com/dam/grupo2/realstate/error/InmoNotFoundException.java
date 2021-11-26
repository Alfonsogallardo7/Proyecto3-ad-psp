package com.dam.grupo2.realstate.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InmoNotFoundException extends RuntimeException{

    public InmoNotFoundException(Long id){
        super("No se puede encontrar la inmobiliaria con el ID: "+id );
    }

    public InmoNotFoundException(){
        super("No se pudieron encontrar inmobiliarias");
    }

}
