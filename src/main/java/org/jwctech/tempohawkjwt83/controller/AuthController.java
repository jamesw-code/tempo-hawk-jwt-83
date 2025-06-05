package org.jwctech.tempohawkjwt83.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jwctech.tempohawkjwt83.payload.request.LogInRequest;
import org.jwctech.tempohawkjwt83.payload.request.SignUpRequest;
import org.jwctech.tempohawkjwt83.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping (path = "/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.newUser(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> logIn(@RequestBody @Valid LogInRequest request) {
        String token = userService.logIn(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        userService.logOut(token);
        return ResponseEntity.ok("Logged out");
    }
}
