/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.configs;

import com.example.ecommercedemo.demo.services.UserPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author The_Humble_Fool
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserPersistenceService userPersistenceService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/secured/**").hasAuthority("USER").antMatchers("/", "/**").permitAll();
//        http.formLogin().loginPage("/login").successHandler(successHandler()).failureUrl("/login-error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.formLogin().loginPage("/login").failureUrl("/login-error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.httpBasic();
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
//            String referer = request.getParameter("referer");
//            response.sendRedirect(referer);
//        };
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPersistenceService).passwordEncoder(passwordEncoder());
    }
}
