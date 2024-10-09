package co.edu.escuelaing.springsecurity.repository.imp;

import co.edu.escuelaing.springsecurity.model.User;
import co.edu.escuelaing.springsecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRepositoryImp implements UserRepository {

    private final Map<String, User> users = new HashMap<>() ;

    @Override
    public User findByEmail(String username) {
        return users.get(username);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }
}
