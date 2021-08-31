package com.example.wv.service;

import com.example.wv.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserRepositoryUserDetailsService(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("用户 '" + username + "' 不存在");
    }
}


