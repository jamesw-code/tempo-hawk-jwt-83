package org.jwctech.tempohawkjwt83.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jwctech.tempohawkjwt83.payload.request.SignInRequest;
import org.jwctech.tempohawkjwt83.payload.request.UserRequest;
import org.jwctech.tempohawkjwt83.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping (path = "/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.newUser(userRequest));
    }

    @PostMapping(path = "/login")
    public String logIn(@RequestBody SignInRequest request) {
        return userService.logIn(request.getUsername(), request.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        userService.logOut(token);
        return ResponseEntity.ok("Logged out");
    }
}
