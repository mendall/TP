package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class is responsible for configuring the security settings of the application.
 * It extends the WebSecurityConfigurerAdapter class and overrides its methods to customize
 * the security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_URLS = {"/public/**","/index.html","/login"};
    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String DEFAULT_USER = "user";
    private static final String DEFAULT_ADMIN = "admin";
    private static final String DEFAULT_PASSWORD = "password";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureRequestAuthorization(http);
        configureFormAndLogout(http);
    }

    private void configureRequestAuthorization(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS)
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    private void configureFormAndLogout(HttpSecurity http) throws Exception {
        http.formLogin().permitAll();
        http.logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        configureAuthentication(auth, DEFAULT_USER, ROLE_USER);
        configureAuthentication(auth, DEFAULT_ADMIN, ROLE_USER, ROLE_ADMIN);
    }

    private void configureAuthentication(AuthenticationManagerBuilder auth, String username, String... roles) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(username)
                .password(passwordEncoder().encode(DEFAULT_PASSWORD))
                .roles(roles);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
