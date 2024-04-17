package org.jwctech.tempohawkjwt83.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    String username;

    String fullName;

    String email;
}
