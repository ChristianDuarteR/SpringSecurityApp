package co.edu.escuelaing.springsecurity.dto;

import java.util.Date;

public record TokenDto(

        String token,

        Date expirationDate) {

}

