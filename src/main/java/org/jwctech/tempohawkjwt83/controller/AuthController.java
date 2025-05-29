package org.jwctech.tempohawkjwt83.controller;

import lombok.RequiredArgsConstructor;
import org.jwctech.tempohawkjwt83.payload.request.SignInRequest;
import org.jwctech.tempohawkjwt83.payload.request.UserRequest;
import org.jwctech.tempohawkjwt83.service.JwtService;
import org.jwctech.tempohawkjwt83.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final UserService userService;

    @PostMapping (path = "/signup", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.newUser(userRequest));
    }

    @PostMapping(path = "/signin", consumes = APPLICATION_JSON_VALUE)
    public String signIn(@RequestBody SignInRequest request) {
        return userService.signIn(request.getUsername(), request.getPassword());
    }

    @PostMapping(path = "/signout", consumes = APPLICATION_JSON_VALUE)
    public String signOut() {
        return "Signed Out!";
    }

    @PostMapping(path = "/remove", consumes = APPLICATION_JSON_VALUE)
    public String remove() {
        return "User removed!";
    }

    @PutMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
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
