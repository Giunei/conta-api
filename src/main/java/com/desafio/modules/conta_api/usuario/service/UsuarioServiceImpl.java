package com.desafio.modules.conta_api.usuario.service;

import com.desafio.modules.conta_api.exceptions.UserFoundException;
import com.desafio.modules.conta_api.usuario.entity.UsuarioEntity;
import com.desafio.modules.conta_api.usuario.respository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity salvar(UsuarioEntity usuario) {
        this.usuarioRepository
                .findByUsername(usuario.getUsername())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        String password = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(password);

        return this.usuarioRepository.save(usuario);
    }
}
