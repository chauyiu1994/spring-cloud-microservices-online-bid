package com.chauyiu1994.onlineBidUsersService.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @NonNull
    private AuthenticationManager authenticationManager;

    @Value("${authorization.token.header.name}")
    private String authorizationHeader;

    @Value("${authorization.token.header.prefix}")
    private String authTokenPrefix;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getToken(ServerWebExchange swe) {

        ServerHttpRequest request = swe.getRequest();
        String authHeaderStr = request.getHeaders().getFirst(authorizationHeader);
        if (authHeaderStr != null && authHeaderStr.startsWith(authTokenPrefix)) {
            return authHeaderStr.replace(authTokenPrefix, "");
        }
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {

        String authToken = getToken(swe);
        if (authToken != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth)
                    .map(authentication -> new SecurityContextImpl(authentication));
        } else {
            return Mono.empty();
        }
    }
}