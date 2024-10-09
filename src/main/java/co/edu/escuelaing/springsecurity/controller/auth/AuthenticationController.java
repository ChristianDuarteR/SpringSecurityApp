package co.edu.escuelaing.springsecurity.controller.auth;


import co.edu.escuelaing.springsecurity.dto.LoginDto;
import co.edu.escuelaing.springsecurity.dto.TokenDto;
import co.edu.escuelaing.springsecurity.exceptions.InvalidCredentialsException;
import co.edu.escuelaing.springsecurity.exceptions.UserAlreadyExistException;
import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.User;
import co.edu.escuelaing.springsecurity.security.JwtUtil;
import co.edu.escuelaing.springsecurity.service.imp.UserServicesImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserServicesImp userServicesImp;

    private final JwtUtil jwtUtil;


    public AuthenticationController(UserServicesImp userServicesImp, JwtUtil jwtUtil) {
        this.userServicesImp = userServicesImp;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws InvalidCredentialsException, UserNameNotFoundException, UserAlreadyExistException, UserNameNotFoundException {
        User user = userServicesImp.findUserByEmail(loginDto.getUsername());
        if (user != null) {
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                TokenDto tokenDto = jwtUtil.generateToken(user.getEmail(), user.getRoles());
                return ResponseEntity.ok(tokenDto);
            }else {
                throw new InvalidCredentialsException();
            }
        }else {
            throw new InvalidCredentialsException();
        }
    }
}
