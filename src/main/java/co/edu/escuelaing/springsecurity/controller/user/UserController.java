package co.edu.escuelaing.springsecurity.controller.user;

import co.edu.escuelaing.springsecurity.exceptions.UserAlreadyExistException;
import co.edu.escuelaing.springsecurity.exceptions.UserBadRequestException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.service.UserServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
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

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<MyUser>> findUsers() {
        List<MyUser> users = userServices.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{email}")
    public void findUser(@PathVariable String email, HttpServletResponse response) throws IOException {
        try {
            MyUser user = userServices.findUserByEmail(email);
            String redirectUrl = "https://taller-apache-security.duckdns.org/user.html?name="
                    + URLEncoder.encode(user.getUsername(), "UTF-8")
                    + "&password=" + URLEncoder.encode(user.getPassword(), "UTF-8")  // Aquí probablemente deberías usar el email en lugar de la contraseña.
                    + "&id=" + URLEncoder.encode(String.valueOf(user.getId()), "UTF-8")
                    + "&role=" + URLEncoder.encode(user.getRole(), "UTF-8");
            response.sendRedirect(redirectUrl);
        } catch (UserNameNotFoundException e) {
            response.sendRedirect("https://taller-apache-security.duckdns.org/error.html");
        }
    }



    // Actualizar un usuarios
    @PutMapping("{email}")
    public void updateUser(@PathVariable String email, @ModelAttribute MyUser userDto, HttpServletResponse response) throws IOException {
        try {
            MyUser user = userServices.findUserByEmail(email);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userServices.updateUser(user, userDto);
            response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
        } catch (UserNameNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    // Eliminar un usuario
    @DeleteMapping("{email}")
    public void deleteUser(@PathVariable String email, HttpServletResponse response) throws IOException {
        try {
            MyUser user = userServices.findUserByEmail(email);
            userServices.deleteUser(user);
            response.sendRedirect("https://taller-apache-security.duckdns.org/index.html");
        } catch (UserNameNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }
}
