package co.edu.escuelaing.springsecurity.service;

import co.edu.escuelaing.springsecurity.exceptions.RestFullNotFound;
import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.repository.RestFullRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class RestFullServices {

    private final RestFullRepository restFullRepository;

    public RestFullServices(@Autowired RestFullRepository restFullRepository) {
        this.restFullRepository = restFullRepository;
    }

    public List<RestFull> getAll() {
        return restFullRepository.findAll();
    }

    public RestFull create(RestFull restFull) {
        restFullRepository.save(restFull);
        return restFull;
    }

    public RestFull updateRestFull(String id, RestFull realEntityData) throws RestFullNotFound {
        Optional<RestFull> optionalRestFull = restFullRepository.findById(id);
        if(optionalRestFull.isPresent()) {
            RestFull restFull = optionalRestFull.get();
            restFull.setTime(realEntityData.getTime());
            restFull.setName(realEntityData.getName());
            restFull.setUsed(realEntityData.isUsed());
            return restFull;
        }else {
            throw new RestFullNotFound("RestFull No existe");
        }
    }

    public void deleteRestFull(String id) throws RestFullNotFound {
        Optional<RestFull> optionalRestFull = restFullRepository.findById(id);
        if (optionalRestFull.isPresent()) {
            RestFull restFull = optionalRestFull.get();
            restFullRepository.delete(restFull);
        }else {
            throw new RestFullNotFound("RestFull No existe");
        }
    }
}
