package co.edu.escuelaing.springsecurity.repository.imp;

import co.edu.escuelaing.springsecurity.model.User;
import co.edu.escuelaing.springsecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImp implements UserRepository {
    @Override
    public User findByEmail(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void save(User user) {

    }
}
