package co.edu.escuelaing.springsecurity;

import co.edu.escuelaing.springsecurity.exceptions.UserNameNotFoundException;
import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.service.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServices userServices;

    @Test
    public void testFindUsers() throws Exception {
        List<MyUser> users = List.of(new MyUser());
        users.getFirst().setUsername("test@example.com");
        users.getFirst().setPassword("password");
        when(userServices.findUsers()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        MyUser user = new MyUser();
        user.setUsername("test@example.com");
        when(userServices.findUserByEmail("test@example.com")).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/test@example.com"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testFindUserByEmailNotFound() throws Exception {
        when(userServices.findUserByEmail("unknown@example.com")).thenThrow(new UserNameNotFoundException());

        mockMvc.perform(get("/api/v1/users/unknown@example.com"))
                .andExpect(status().is3xxRedirection());
    }

    // Similar tests can be added for PUT and DELETE
}
