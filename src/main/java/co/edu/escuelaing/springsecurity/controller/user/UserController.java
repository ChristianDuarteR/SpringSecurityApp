package co.edu.escuelaing.springsecurity.controller.user;

import co.edu.escuelaing.springsecurity.exceptions.UserAlreadyExistException;
import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.User;
import co.edu.escuelaing.springsecurity.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServices userServices;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers(){
        List<User> users = userServices.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{email}")
    public ResponseEntity<User> findUser(@PathVariable String email) throws UserAlreadyExistException {
        try {
            User user = userServices.findUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch ( UserNameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userDto) {
        try {
            userServices.findUserByEmail(userDto.getEmail());
            throw new UserAlreadyExistException();
        }catch (UserNameNotFoundException e){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = new User(userDto);
            try {
                userServices.createUser(user);
            }catch (UserBadRequestException ex){
                return ResponseEntity.badRequest().build();
            }
            URI createdUserUri = URI.create("/api/v1/users/" + user.getEmail());
            return ResponseEntity.created(createdUserUri).body(user);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PutMapping("{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User userDto)  {
        try {
            User user = userServices.findUserByEmail(email);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userServices.updateUser(user, userDto);
            return ResponseEntity.ok(user);
        } catch (UserNameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("{email}")
    public ResponseEntity<User> deleteUser(@PathVariable String email) throws UserAlreadyExistException {
        try {
            User user = userServices.findUserByEmail(email);
            userServices.deleteUser(user);
            return ResponseEntity.noContent().build();
        } catch (UserNameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
