package com.desafio.modules.conta_api.usuario.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.desafio.modules.conta_api.usuario.respository.UsuarioRepository;
import com.desafio.modules.conta_api.usuario.dto.AuthContaResponseDTO;
import com.desafio.modules.conta_api.usuario.dto.AuthUsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthUsuarioServiceImpl implements AuthUsuarioService {

    @Value("${security.token.secret.conta}")
    private String secretKey;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthContaResponseDTO execute(AuthUsuarioRequestDTO authUsuarioRequestDTO) throws AuthenticationException {
        var conta = this.usuarioRepository.findByUsername(authUsuarioRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder
                .matches(authUsuarioRequestDTO.senha(), conta.getSenha());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        List<String> roles = List.of("usuario");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plus(Duration.ofHours(30));
        var token = JWT.create()
                .withIssuer("desafio-conta")
                .withSubject(conta.getId().toString())
                .withClaim("roles", roles)
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        this.usuarioRepository.findByUsername(authUsuarioRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorreto"));

        return AuthContaResponseDTO.builder()
                .tokenAcesso(token)
                .expiraEm(expiresIn.toEpochMilli())
                .roles(roles)
                .build();
    }
}
