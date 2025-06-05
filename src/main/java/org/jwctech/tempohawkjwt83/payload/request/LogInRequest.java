package org.jwctech.tempohawkjwt83.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
