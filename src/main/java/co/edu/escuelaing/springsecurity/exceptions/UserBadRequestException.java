package co.edu.escuelaing.springsecurity.exceptions;

public class UserBadRequestException extends Exception{
    public UserBadRequestException(){
        super("La solicitud no pudo ser procesada porfavor verifiquela e intente nuevamente");
    }
}
