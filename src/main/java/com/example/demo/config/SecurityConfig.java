package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("password")).roles("ADMIN");
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/books").permitAll()
                .antMatchers(HttpMethod.GET, "/books/*").permitAll()
                .antMatchers(HttpMethod.POST, "/books").permitAll()
                .antMatchers(HttpMethod.PATCH, "/books/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/books/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
