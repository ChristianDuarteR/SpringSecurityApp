package co.edu.escuelaing.springsecurity.repository.imp;


import co.edu.escuelaing.springsecurity.model.RestFull;
import co.edu.escuelaing.springsecurity.repository.RestFullRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestFullRepositoryImp implements RestFullRepository {
    private final Map<String, RestFull> restFullEntities = new HashMap<>() ;

    public RestFullRepositoryImp(){
        setUpRestFull();
    }

    private void setUpRestFull(){
        restFullEntities.put("GET", new RestFull("GET", System.currentTimeMillis(),true ));
        restFullEntities.put("POST", new RestFull("POST", System.currentTimeMillis(),true ));
        restFullEntities.put("PUT", new RestFull("PUT", System.currentTimeMillis(),false ));
    }

    @Override
    public List<RestFull> getAll() {
        return (List<RestFull>) restFullEntities.values();
    }
}
