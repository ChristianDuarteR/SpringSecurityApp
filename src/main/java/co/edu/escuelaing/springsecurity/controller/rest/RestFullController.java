package co.edu.escuelaing.springsecurity.controller.rest;


import co.edu.escuelaing.springsecurity.exceptions.RestFullNotFound;
import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.service.RestFullServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<RestFull>> getData(){
        List<RestFull> restFulls = restFullServices.getAll();
        return ResponseEntity.ok(restFulls);
    }

    @PostMapping
    public ResponseEntity<RestFull> createRealEntity(@RequestBody RestFull restFull) throws URISyntaxException {
        RestFull real = restFullServices.create(restFull);

        URI uri = new URI("api/v1/restfull/"+ real.getId());
        return ResponseEntity.created(uri).body(real);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestFull> updateRealEntity(@PathVariable String id, @RequestBody RestFull realEntityData){
        try {
            RestFull real = restFullServices.updateRestFull(id, realEntityData);
            URI uri = new URI("api/v1/realentitys/" + realEntityData.getId());
            return ResponseEntity.created(uri).body(real);
        }catch (URISyntaxException e){
            return ResponseEntity.badRequest().build();
        } catch (RestFullNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RestFull> deleteRealEntity(@PathVariable String id){
        try {
            restFullServices.deleteRestFull(id);
            return ResponseEntity.noContent().build();
        }catch (RestFullNotFound e){
            return ResponseEntity.notFound().build();
        }
    }
}
