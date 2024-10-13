package co.edu.escuelaing.springsecurity.exceptions;

public class UserNameNotFoundException extends Exception{
    public UserNameNotFoundException(){
        super("No fue posible encontrar al usuario");
    }
}