package com.splitwizard.splitwizard.Jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter @Setter
public class UserDetailsImpl implements UserDetails {
    private String account;
    private String password;
    private List<SimpleGrantedAuthority> authorities;
    private Integer id;
    private String UID;
    private String memberName;

    public UserDetailsImpl(String account, String password, List<SimpleGrantedAuthority> authorities,
                           Integer id, String UID, String memberName) {
        this.account = account;
        this.password = password;
        this.id = id;
        this.authorities = authorities;
        this.UID = UID;
        this.memberName = memberName;
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
        return account;
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
