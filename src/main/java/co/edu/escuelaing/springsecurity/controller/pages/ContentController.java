package co.edu.escuelaing.springsecurity.controller.pages;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String handleLogin(){
        return "custom_login";
    }
}
