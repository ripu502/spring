package com.demo.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    public Algorithm getJWTAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return algorithm;
    }

    public String createAccessToken(User user, String issuer) {
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000))
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities().stream ().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(getJWTAlgorithm());
        return access_token;
    }

    public String createRefreshToken(User user, String issuer) {
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000))
                .withIssuer(issuer)
                .sign(getJWTAlgorithm());
        return refresh_token;
    }
}
