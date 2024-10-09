package co.edu.escuelaing.springsecurity.exceptions;

public abstract class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super(message);
    }
}