package co.edu.escuelaing.springsecurity.model;

import co.edu.escuelaing.springsecurity.utils.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    @Setter
    private String name;
    @Setter
    private String lastName;
    @Setter
    private String password;
    @Setter
    private List<RoleEnum> roles;


    public User(User userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.password = userDto.getPassword();
        this.roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }
}

