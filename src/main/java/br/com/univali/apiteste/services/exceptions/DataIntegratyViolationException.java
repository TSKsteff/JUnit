package br.com.univali.apiteste.services.exceptions;

public class DataIntegratyViolationException extends  RuntimeException {
    public DataIntegratyViolationException(String menssage){
        super(menssage);
    }
}
