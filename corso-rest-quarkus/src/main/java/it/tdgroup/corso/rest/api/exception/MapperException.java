package it.tdgroup.corso.rest.api.exception;


public class MapperException extends Exception {

    public MapperException(Throwable cause) {
        super(cause);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperException(String message) {
        super(message);
    }

    public MapperException() {
    }
    
    

}
