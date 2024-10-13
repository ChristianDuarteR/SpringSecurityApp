package co.edu.escuelaing.springsecurity.controller.user;

import co.edu.escuelaing.springsecurity.exceptions.UserAlreadyExistException;
import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.MyUser;
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
    public ResponseEntity<List<MyUser>> findUsers(){
        List<MyUser> users = userServices.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{email}")
    public ResponseEntity<MyUser> findUser(@PathVariable String email) {
        try {
            MyUser user = userServices.findUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch ( UserNameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{email}")
    public ResponseEntity<MyUser> updateUser(@PathVariable String email, @RequestBody MyUser userDto)  {
        try {
            MyUser user = userServices.findUserByEmail(email);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userServices.updateUser(user, userDto);
            return ResponseEntity.ok(user);
        } catch (UserNameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{email}")
    public ResponseEntity<MyUser> deleteUser(@PathVariable String email) throws UserAlreadyExistException {
        try {
            MyUser user = userServices.findUserByEmail(email);
            userServices.deleteUser(user);
            return ResponseEntity.noContent().build();
        } catch (UserNameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}