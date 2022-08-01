package com.io.jst.security.config;

import com.io.jst.security.oauth.PrincipleOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipleOauth2UserService principleOauth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/user/addPay").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/user/userDetail").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/user/userDetailEdit").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/shop/detail/**").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/shop/payment/**").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/shop/successForm").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/board/FreeBoardAdd").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/board/FreeBoardDetail").access("hasRole('ROLE_BASIC') or hasRole('ROLE_ADMIN') or hasRole('ROLE_VIP')")
                .mvcMatchers("/shop/add").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        http
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginProc")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/user/login")
                .userInfoEndpoint()
                .userService(principleOauth2UserService);

        return http.build();
    }


    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    }
}
