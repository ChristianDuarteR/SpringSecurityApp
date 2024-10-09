package co.edu.escuelaing.springsecurity.controller.rest;


import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.service.imp.RestFullServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restfull")
public class RestFullController {

    private final RestFullServices restFullServices;

    public RestFullController(@Autowired RestFullServices restFullServices) {
        this.restFullServices = restFullServices;
    }

    @GetMapping
    public ResponseEntity<List<RestFull>> getData(){
        List<RestFull> restFulls = restFullServices.getAll();
        return ResponseEntity.ok(restFulls);
    }
}
