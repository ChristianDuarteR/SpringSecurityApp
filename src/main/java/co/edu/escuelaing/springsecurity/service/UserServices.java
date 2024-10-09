package co.edu.escuelaing.springsecurity.service;

import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.User;

import java.util.List;

public interface UserServices {
    List<User> findUsers();

    User findUserByEmail(String email) throws UserNameNotFoundException;

    void createUser(User user) throws UserBadRequestException;

    void deleteUser(User user);

    void updateUser(User user, User userDto);
}
