package co.edu.escuelaing.springsecurity.controller.user;


import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {

    private final MyUserRepository myUserRepository;

    private final PasswordEncoder passwordEncoder;


    public RegistrationController(@Autowired MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser myUser) throws URISyntaxException {
        myUser.setRole("USER");
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        URI uri = new URI("api/v1/register/" + myUser.getId());
        return ResponseEntity.created(uri).body(myUser);
    }
}
