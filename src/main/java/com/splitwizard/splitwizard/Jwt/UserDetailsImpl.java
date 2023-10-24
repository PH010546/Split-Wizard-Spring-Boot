package com.splitwizard.splitwizard.Jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter @Setter
public class UserDetailsImpl implements UserDetails {
    private String userName;
    private String password;
    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private Integer userId;

    public UserDetailsImpl(String userName, String password, SimpleGrantedAuthority authority, Integer userId) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        authorities.add(authority);
    }

    public UserDetailsImpl(String userName, String password, List<SimpleGrantedAuthority> authorities, Integer userId) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
