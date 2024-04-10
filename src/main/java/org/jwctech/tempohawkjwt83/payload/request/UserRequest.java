package org.jwctech.tempohawkjwt83.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    String username;

    String fullName;

    String password;

    String email;

}
