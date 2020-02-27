package com.chauyiu1994.onlineBidDiscoveryService;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// secure eureka dashboard
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // disable csrf for microservices to register with eureka
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
