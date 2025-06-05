package org.jwctech.tempohawkjwt83.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    String username;

    String fullName;

    @NotBlank
    String password;

    String email;

}
