package co.edu.escuelaing.springsecurity.repository;

import co.edu.escuelaing.springsecurity.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    User findByEmail(String username);

    List<User> findAll();

    void delete(User user);

    void save(User user);
}
