package co.edu.escuelaing.springsecurity.controller.rest;


import co.edu.escuelaing.springsecurity.exceptions.RestFullNotFound;
import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.service.RestFullServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restfull")
@CrossOrigin(origins = "*")
public class RestFullController {

    private final RestFullServices restFullServices;

    public RestFullController(@Autowired RestFullServices restFullServices) {
        this.restFullServices = restFullServices;
    }

    // Obtener todos los registros de RestFull
    @GetMapping
    public void getData(HttpServletResponse response) throws IOException {
        List<RestFull> restFulls = restFullServices.getAll();
        // Redirige a una página de vista general si es necesario
        response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
    }

    // Crear una nueva entidad RestFull
    @PostMapping
    public void createRestFull(@ModelAttribute RestFull restFull, HttpServletResponse response) throws IOException {
        RestFull real = restFullServices.create(restFull);
        // Redirige después de crear exitosamente
        response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
    }

    // Actualizar una entidad RestFull existente
    @PutMapping("{id}")
    public void updateRestFull(@PathVariable String id, @ModelAttribute RestFull realEntityData, HttpServletResponse response) throws IOException {
        try {
            RestFull real = restFullServices.updateRestFull(id, realEntityData);
            // Redirige después de la actualización exitosa
            response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
        } catch (RestFullNotFound e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "RestFull not found");
        }
    }

    // Eliminar una entidad RestFull
    @DeleteMapping("{id}")
    public void deleteRestFull(@PathVariable String id, HttpServletResponse response) throws IOException {
        try {
            restFullServices.deleteRestFull(id);
            // Redirige después de la eliminación exitosa
            response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
        } catch (RestFullNotFound e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "RestFull not found");
        }
    }
}

