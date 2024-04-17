package org.jwctech.tempohawkjwt83.service;

import org.jwctech.tempohawkjwt83.model.User;
import org.jwctech.tempohawkjwt83.payload.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User newUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setFullName(userRequest.getFullName());
        user.setPassword(userRequest.getPassword());

        return user;
    }
}
