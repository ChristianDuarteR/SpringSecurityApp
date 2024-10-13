package co.edu.escuelaing.springsecurity.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class MyUser {
    @Id
    private String id;

    private String username;

    private String password;

    private String role;

    public MyUser(MyUser userDto) {

    }
}
