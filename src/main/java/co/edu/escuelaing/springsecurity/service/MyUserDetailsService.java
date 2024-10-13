package co.edu.escuelaing.springsecurity.service;

import co.edu.escuelaing.springsecurity.model.MyUser;
import co.edu.escuelaing.springsecurity.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    public MyUserDetailsService(@Autowired MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser optionalMyUser = myUserRepository.findByUsername(username);
        if (optionalMyUser != null){
            return User.builder()
                    .username(optionalMyUser.getUsername())
                    .password(optionalMyUser.getPassword())
                    .roles(getRoles(optionalMyUser))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String getRoles(MyUser myUser) {
        return "USER";
    }
}
