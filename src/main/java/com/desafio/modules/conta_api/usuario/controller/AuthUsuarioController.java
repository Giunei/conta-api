package com.desafio.modules.conta_api.usuario.controller;

import com.desafio.modules.conta_api.usuario.dto.AuthUsuarioRequestDTO;
import com.desafio.modules.conta_api.usuario.service.AuthUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class AuthUsuarioController {

    @Autowired
    private AuthUsuarioService authUsuarioService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthUsuarioRequestDTO authUsuarioRequestDTO) {
        try {
            var token = this.authUsuarioService.execute(authUsuarioRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
