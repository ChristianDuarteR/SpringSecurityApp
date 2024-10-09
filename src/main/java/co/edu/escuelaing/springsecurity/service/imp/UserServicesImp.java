package co.edu.escuelaing.springsecurity.service.imp;

import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.User;
import co.edu.escuelaing.springsecurity.repository.UserRepository;
import co.edu.escuelaing.springsecurity.service.UserServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) throws UserNameNotFoundException {
        if (userRepository.findByEmail(email) == null){
            throw new UserNameNotFoundException();
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(User user) throws UserBadRequestException {
        if(user.getName() == null
                || user.getLastName() == null
                || user.getEmail() == null
                || user.getPassword() == null) throw new UserBadRequestException();
        else {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user, User userDto) {
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
}
