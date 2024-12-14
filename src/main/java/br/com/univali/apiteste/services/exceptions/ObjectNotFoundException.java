package br.com.univali.apiteste.services.exceptions;

public class ObjectNotFoundException extends  RuntimeException {
    public ObjectNotFoundException(String menssage){
        super(menssage);
    }
}
