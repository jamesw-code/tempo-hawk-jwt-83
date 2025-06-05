package org.jwctech.tempohawkjwt83.service;

import lombok.RequiredArgsConstructor;
import org.jwctech.tempohawkjwt83.model.User;
import org.jwctech.tempohawkjwt83.payload.request.JwtPayload;
import org.jwctech.tempohawkjwt83.payload.request.SignUpRequest;
import org.jwctech.tempohawkjwt83.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final GopherDbClient gopherDbClient;
    private final PasswordEncoder passwordEncoder;

    public  void newUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setFullName(signUpRequest.getFullName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setScopes(Set.of("GUEST"));

        userRepo.save(user);
    }

    public String logIn(String username, String rawPassword) {
        User user = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Verify password
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        JwtPayload payload = new JwtPayload(
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getScopes()
        );

        return jwtService.generateJWT(payload);
    }

    public void logOut(String token) {
        String jwt = token.replace("Bearer ", "");
        long exp = jwtService.getExpirationFromJWT(jwt);
        gopherDbClient.set(jwt, String.valueOf(exp)); // Store in GopherDb
    }
}
