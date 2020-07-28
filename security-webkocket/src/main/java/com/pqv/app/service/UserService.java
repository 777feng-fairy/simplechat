package com.pqv.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pqv.app.bean.User;
import com.pqv.app.mapper.UserMapper;

@Component
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserMapper mapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.sLogin(username);
        if(user==null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return user;
    }

}
