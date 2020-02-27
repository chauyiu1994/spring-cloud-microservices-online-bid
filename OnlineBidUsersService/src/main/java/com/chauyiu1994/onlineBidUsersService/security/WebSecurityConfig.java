package com.chauyiu1994.onlineBidUsersService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    private Environment env;
    private List<String> whiteListInboundIp;

    public WebSecurityConfig(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository, Environment env) {

        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
        this.env = env;
        whiteListInboundIp = new ArrayList<>();
        whiteListInboundIp.add(env.getProperty("gateway.ip"));
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        System.out.println("fdvfvfddb");
        http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                }).accessDeniedHandler((swe, e) -> {
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                }).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                //.pathMatchers("/**").access(this::checkWhiteListIp)
                .pathMatchers(env.getProperty("registration.url.path")).permitAll()
                .pathMatchers(env.getProperty("login.url.path")).permitAll()
                .pathMatchers(env.getProperty("resetPassword.url.path")).permitAll()
                .anyExchange().authenticated();

        return http.build();
    }

    private Mono<AuthorizationDecision> checkWhiteListIp(Mono<Authentication> auth, AuthorizationContext ctx) {

        String ip = ctx.getExchange().getRequest().getRemoteAddress().getAddress().toString().replace("/", "");
        System.out.println(ip);
        return auth.map(a -> new AuthorizationDecision(a.isAuthenticated()))
                .defaultIfEmpty(new AuthorizationDecision(
                        whiteListInboundIp.contains(ip) ? true : false
                ));
    }
}
