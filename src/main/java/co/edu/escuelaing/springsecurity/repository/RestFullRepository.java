package co.edu.escuelaing.springsecurity.repository;

import co.edu.escuelaing.springsecurity.model.RestFull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestFullRepository extends MongoRepository<RestFull, String> {

}
