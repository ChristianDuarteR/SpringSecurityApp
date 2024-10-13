package co.edu.escuelaing.springsecurity.repository;

import co.edu.escuelaing.springsecurity.model.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends MongoRepository<MyUser, String> {
    MyUser findByUsername(String username);
}
