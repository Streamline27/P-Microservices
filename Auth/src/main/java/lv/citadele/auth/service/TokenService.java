package lv.citadele.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lv.citadele.auth.api.TokenResponse;
import lv.citadele.auth.model.UserCredentials;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class TokenService {

    public TokenResponse getFor(UserCredentials credentials) {
        final String accessToken = generateAccessToken(credentials);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private String generateAccessToken(UserCredentials credentials) {
        final Long now = System.currentTimeMillis();
        final Long expirationMoment = now + 10 * 60000;
        final String username = credentials.getUsername();
        final HashMap<String, Object> data = getData(credentials);

        return Jwts.builder()
            .setSubject(username)
            .claim("data", data)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(expirationMoment))  // in milliseconds
            .signWith(SignatureAlgorithm.HS512, "secret".getBytes())
            .compact();
    }

    private HashMap<String, Object> getData(UserCredentials credentials) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role",     credentials.getRole().toString());
        claims.put("name",     credentials.getName());
        claims.put("id",       credentials.getId());
        claims.put("username", credentials.getUsername());
        return claims;
    }
}
