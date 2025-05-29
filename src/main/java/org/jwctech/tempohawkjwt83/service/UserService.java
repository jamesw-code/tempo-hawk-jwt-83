package org.jwctech.tempohawkjwt83.service;

import lombok.RequiredArgsConstructor;
import org.jwctech.tempohawkjwt83.model.User;
import org.jwctech.tempohawkjwt83.payload.request.UserRequest;
import org.jwctech.tempohawkjwt83.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public  User newUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setFullName(userRequest.getFullName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setScopes(Set.of("GUEST"));

        return userRepo.save(user);
    }

    public String signIn(String username, String rawPassword) {
        User user = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Verify password
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Build claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("scopes", user.getScopes());
//        claims.put("role", user.getRoles());


        return jwtService.generateJWT(claims);
    }
}
