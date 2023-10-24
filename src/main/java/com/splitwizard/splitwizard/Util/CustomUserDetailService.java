package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.DAO.MemberRepository;
import com.splitwizard.splitwizard.POJO.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    MemberRepository dao;
    @Autowired
    CustomUserDetailService(MemberRepository dao){
        this.dao = dao;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // Step 1: Retrieve the member details from your data source, for example, a database.
        Member member = dao.findByAccount(account);

        // Step 2: Check if the member exists in the data source. If not, throw an exception.
        if (member == null) {
            throw new UsernameNotFoundException("User not found with account: " + account);
        }

        // Step 3: Create a UserDetails object representing the member.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getAuthority());
        List<SimpleGrantedAuthority> authorities = List.of(authority);
        // Step 4: Return the UserDetails object.
        return new User(
                member.getAccount(),       // Username
                member.getPassword(),       // Password
                // You can pass an empty list of GrantedAuthority objects since roles are not used.
                authorities
        );
    }
}
