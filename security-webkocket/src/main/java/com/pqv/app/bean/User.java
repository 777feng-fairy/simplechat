package com.pqv.app.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import lombok.Data;

@Data
public class User implements UserDetails {
   
   
    private static final long serialVersionUID = 1L;
    private String account;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set= new HashSet<>();
        set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return set;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return account;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
   
}
