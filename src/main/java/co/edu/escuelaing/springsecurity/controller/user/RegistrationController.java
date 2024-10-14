package co.edu.escuelaing.springsecurity.controller.user;


import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.repository.MyUserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping()
    public void addUser(@ModelAttribute MyUser user, HttpServletResponse response) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        myUserRepository.save(user);
        response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
    }
}
