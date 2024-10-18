package co.edu.escuelaing.springsecurity;

import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.repository.MyUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserRepository myUserRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAddUser() throws Exception {
        MyUser user = new MyUser();
        user.setUsername("new@example.com");
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/v1/register")
                        .param("username", "newUser")
                        .param("email", "new@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://taller-apache-security.duckdns.org/index.html"));

        verify(myUserRepository, times(1)).save(any(MyUser.class));
    }
}
