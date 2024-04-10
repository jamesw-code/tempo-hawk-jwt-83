package org.jwctech.tempohawkjwt83.controller;

import lombok.RequiredArgsConstructor;
import org.jwctech.tempohawkjwt83.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping (path = "/signup", consumes = APPLICATION_JSON_VALUE)
    public String signUp() {
        return "Signed Up!";
    }

    @PostMapping(path = "/signIn", consumes = APPLICATION_JSON_VALUE)
    public String signIn() {
        return "Signed In!";
    }

    @PostMapping(path = "/signout", consumes = APPLICATION_JSON_VALUE)
    public String signOut() {
        return "Signed Out!";
    }

    @PostMapping(path = "/remove", consumes = APPLICATION_JSON_VALUE)
    public String remove() {
        return "User removed!";
    }

    @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
    public String validateToken() {
        return "Valid token!";
    }

    @PostMapping(path = "/refresh", consumes = APPLICATION_JSON_VALUE)
    public String refreshToken() {
        return "new refresh token!";
    }

    /**
     * Method to Create a JWT with Claims
     * @param claims - Key value pair of info for JWT
     * @return JWT with Claims
     */
    @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
    public String getToken(@RequestBody Map<String, Object> claims) {
        return jwtService.generateJWT(claims);
    }

}