package com.splitwizard.splitwizard.Util;

import com.splitwizard.splitwizard.Jwt.JwtAuthFilter;
import com.splitwizard.splitwizard.Jwt.UnauthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig{

    private final CustomUserDetailService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    @Autowired
    public WebSecurityConfig(CustomUserDetailService userDetailsService,
                             JwtAuthFilter jwtAuthFilter){
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.
//                csrf(AbstractHttpConfigurer::disable)
//                .userDetailsService(userDetailsService)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/login", "/register", "/auth", "/auth/parse").permitAll()
//                        .requestMatchers("/auth").permitAll()
//                        .requestMatchers("/auth/parse").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("http://localhost:3000/splitWizard/login")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll)
                // make single user can only use one session at a time, and will prevent the second login
//                .sessionManagement(session -> session
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true))
//                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/groups"));

                // 因為這裡設定 sessionCreationPolicy(SessionCreationPolicy.STATELESS)，所以
                // 不用 http session 記錄資料，因此 SecurityContextHolder 不會將登入認証成功後的 Authentication 記到
                // http session。也就是說登入認証成功後的 Authentication 不會被記錄到系統，每次的 http request 都必需重新
                // 進行一次登入認証，不然從 SecurityContextHolder.getContext().getAuthentication() 取回登入認証成功後
                // 的 Authentication instance 一定是 null。
                csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsService)
                .exceptionHandling(handle -> handle.authenticationEntryPoint(new UnauthEntryPoint()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .logout((logout) -> logout.logoutUrl("/logout"));
        return http.build();

    }

//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
