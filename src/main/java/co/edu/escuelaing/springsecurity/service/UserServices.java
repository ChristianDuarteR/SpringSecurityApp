package co.edu.escuelaing.springsecurity.service;

import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices  {

    private final MyUserRepository userRepository;

    public UserServices(MyUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<MyUser> findUsers() {
        return userRepository.findAll();
    }

    public MyUser findUserByEmail(String email) throws UserNameNotFoundException {
        if (userRepository.findByUsername(email) == null){
            throw new UserNameNotFoundException();
        }
        return userRepository.findByUsername(email);
    }

    public void createUser(MyUser user) throws UserBadRequestException {
        if(user.getUsername() == null
                || user.getPassword() == null) throw new UserBadRequestException();
        else {
            userRepository.save(user);
        }
    }

    public void deleteUser(MyUser user) {
        userRepository.delete(user);
    }

    public void updateUser(MyUser user, MyUser userDto) {
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

}
