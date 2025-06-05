package org.jwctech.tempohawkjwt83.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jwctech.tempohawkjwt83.AppJwtProperties;
import org.jwctech.tempohawkjwt83.payload.request.JwtPayload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    private final AppJwtProperties appJwtProperties;

    public String generateJWT(JwtPayload payload) {
        byte[] decoded = appJwtProperties.getKey().getEncoded();

        JWSAlgorithm algorithm = appJwtProperties.getAlgorithm();

        JWSHeader header = new JWSHeader(algorithm);
        JWTClaimsSet claimsSet = buildClaimsSet(payload);

        SignedJWT jwt = new SignedJWT(header, claimsSet);

        try {
            MACSigner signer = new MACSigner(decoded);
            jwt.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException("Unable to generate JWT", e);
        }

        return jwt.serialize();
    }

    private JWTClaimsSet buildClaimsSet(JwtPayload payload) {
        String issuer = appJwtProperties.getIssuer();
        Instant issuedAt = Instant.now();
        Instant expirationTime = issuedAt.plus(appJwtProperties.getExpiresIn());

        return new JWTClaimsSet.Builder()
                .issuer(issuer)
                .issueTime(Date.from(issuedAt))
                .expirationTime(Date.from(expirationTime))
                .claim("username", payload.username())
                .claim("email", payload.email())
                .claim("fullName", payload.fullName())
                .claim("scopes", payload.scopes())
                .build();
    }

    public long getExpirationFromJWT(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            Date exp = jwt.getJWTClaimsSet().getExpirationTime();

            if (exp == null) {
                throw new IllegalArgumentException("Token does not contain an expiration");
            }

            return exp.toInstant().getEpochSecond(); // Unix timestamp
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JWT", e);
        }
    }

}
