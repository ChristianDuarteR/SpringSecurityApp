package co.edu.escuelaing.springsecurity.service.imp;

import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.repository.RestFullRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestFullServices {

    private final RestFullRepository restFullRepository;

    public RestFullServices(@Autowired RestFullRepository restFullRepository) {
        this.restFullRepository = restFullRepository;
    }

    public List<RestFull> getAll() {
        return restFullRepository.getAll();
    }
}
