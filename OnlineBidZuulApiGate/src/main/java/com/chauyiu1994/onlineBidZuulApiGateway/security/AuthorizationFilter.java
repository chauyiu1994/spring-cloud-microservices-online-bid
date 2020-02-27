package com.chauyiu1994.onlineBidZuulApiGateway.security;

import com.chauyiu1994.onlineBidZuulApiGateway.shared.Role;
import io.jsonwebtoken.Claims;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private Environment env;

    public AuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, Environment env) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.env = env;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String authHeaderStr = req.getHeader(env.getProperty("authorization.token.header.name"));

        System.out.println(req.getHeader(env.getProperty("authorization.token.header.name")));
        // if token not valid, go to next filter and return
        if (authHeaderStr == null || !authHeaderStr.startsWith(env.getProperty("authorization.token.header.prefix"))) {
            chain.doFilter(req, res);
            return;
        }

        // verify the token
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(authHeaderStr);

        // set the userName to the SecurityContextHolder so that the rest of the application can recognize the user
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication (String authHeaderStr) {

        if (authHeaderStr == null) return null;

        // get a clean token
        String authToken = authHeaderStr.replace(env.getProperty("authorization.token.header.prefix"), "");

        String username;
        try {
            username = jwtUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwtUtil.validateToken(authToken)) {
            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            List<String> rolesMap = claims.get("roles", List.class);
            List<Role> roles = new ArrayList<>();
            for (String roleMap : rolesMap) {
                System.out.println(roleMap);
                roles.add(Role.valueOf(roleMap));
            }
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
            );
            return auth;
        } else {
            return null;
        }
    }
}
