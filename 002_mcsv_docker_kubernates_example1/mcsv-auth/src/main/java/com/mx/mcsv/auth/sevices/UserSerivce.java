package com.mx.mcsv.auth.sevices;

import com.mx.mcsv.auth.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserSerivce implements UserDetailsService {

     @Autowired
     private WebClient.Builder webClient;

     private Logger logs = org.slf4j.LoggerFactory.getLogger(UserSerivce.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            User user = webClient.build()
                    .get()
                    .uri("http://mcsv-users-container:8081/login", uri -> uri.queryParam("email", email).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

            logs.info("User found: " + user.getEmail());

            return new org.springframework.security.core.userdetails.User(
                    email,
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("User not found from auth service");
        }

    }

}
