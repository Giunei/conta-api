package com.desafio.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static String jsonToJSON(Object obj) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(Long id, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create().withIssuer("desafio-conta")
                .withExpiresAt(expiresIn)
                .withClaim("roles", List.of("usuario"))
                .withSubject(id.toString())
                .sign(algorithm);
    }
}
