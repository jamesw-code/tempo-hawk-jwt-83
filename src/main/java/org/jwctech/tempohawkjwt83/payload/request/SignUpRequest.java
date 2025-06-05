package org.jwctech.tempohawkjwt83.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    String username;

    String fullName;

    String password;

    String email;

}
