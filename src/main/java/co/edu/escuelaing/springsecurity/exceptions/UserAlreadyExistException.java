package co.edu.escuelaing.springsecurity.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(){
        super("Usuario ya se encuentra registrado");
    }
}