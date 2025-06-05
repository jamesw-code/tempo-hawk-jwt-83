package org.jwctech.tempohawkjwt83.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping()
    public String any(Authentication authentication) {
        return "Hello, %s. You have next permissions: %s"
                .formatted(getUsername(authentication), authentication.getAuthorities());
    }

    @GetMapping("/guest")
    @PreAuthorize("hasAuthority('SCOPE_GUEST')")
    public String guest(Authentication authentication) {
        return "Hello, %s. You have next permissions: %s"
                .formatted(getUsername(authentication), authentication.getAuthorities());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('SCOPE_GUEST')")
    public String admin(Authentication authentication) {
        return "Hello, %s. You have next permissions: %s"
                .formatted(getUsername(authentication), authentication.getAuthorities());
    }

    private String getUsername(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getClaimAsString("username");
    }

}
