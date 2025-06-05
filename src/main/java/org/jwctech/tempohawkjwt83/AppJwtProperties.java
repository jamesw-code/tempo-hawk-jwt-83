package org.jwctech.tempohawkjwt83;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "app.jwt")
public class AppJwtProperties {

    @NotNull
    private SecretKey key;

    @NotEmpty
    private String issuer;

    @NotNull
    private JWSAlgorithm algorithm;

    @NotNull
    @DurationMin(seconds = 1)
    private Duration expiresIn;

    public void setAlgorithm(String algorithm) {
        this.algorithm = JWSAlgorithm.parse(algorithm);
    }

    public void setKey(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);

        if (decodedKey.length < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 bytes) long.");
        }

        var jwk = new OctetSequenceKey.Builder(decodedKey)
                .algorithm(algorithm)
                .build();

        this.key = jwk.toSecretKey();
    }

}
