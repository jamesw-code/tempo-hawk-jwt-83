package org.jwctech.tempohawkjwt83.payload.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public record JwtPayload(
        String username,
        String email,
        String fullName,
        Set<String> scopes
) {
    public Map<String, Object> toClaimsMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("email", email);
        map.put("fullName", fullName);
        map.put("scopes", scopes);
        return map;
    }
}
