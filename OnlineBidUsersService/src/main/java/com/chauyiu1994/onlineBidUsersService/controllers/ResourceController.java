package com.chauyiu1994.onlineBidUsersService.controllers;

import com.chauyiu1994.onlineBidUsersService.feign.ProductClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ResourceController {
    
    private ProductClient productClient;

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<?>> user() {

        return productClient.getProducts().map(ResponseEntity::ok);
    }

}
