package com.splitwizard.splitwizard.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig{

//    private UserDetailsService userDetailsService;
//    @Autowired
//    public WebSecurityConfig(UserDetailsService userDetailsService){
//        this.userDetailsService = userDetailsService;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.
//                authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/login", "/register").permitAll()
//                        .requestMatchers("/allMembers", "/groups").hasAuthority("user")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("http://localhost:3000/splitWizard/login")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll)
                // make single user can only use one session at a time, and will prevent the second login
                sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true))
                .csrf(AbstractHttpConfigurer::disable)
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/groups"));
        return http.build();

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
