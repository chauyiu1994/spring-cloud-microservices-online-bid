package com.chauyiu1994.onlineBidZuulApiGateway.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment env;
    private JWTUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println(env.getProperty("api.login.url.path"));
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                // exception for h2console and zuul actuator
                .antMatchers(env.getProperty("api.h2console.url.path")).permitAll()
                .antMatchers(env.getProperty("api.zuul.actuator.url.path")).permitAll()
                .antMatchers(env.getProperty("api.users.actuator.url.path")).permitAll()
                //no need to authenticate when performing login or sign up
                .antMatchers(HttpMethod.POST, env.getProperty("api.registration.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, env.getProperty("api.login.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, env.getProperty("api.resetPassword.url.path")).permitAll()
                // any other request should be authenticated
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), jwtUtil, env));

        // tell spring not to create http session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
