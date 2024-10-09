package co.edu.escuelaing.springsecurity.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("invalid username or password");
    }

}
