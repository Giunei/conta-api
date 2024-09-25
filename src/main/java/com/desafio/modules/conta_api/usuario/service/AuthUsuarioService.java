package com.desafio.modules.conta_api.usuario.service;

import com.desafio.modules.conta_api.usuario.dto.AuthUsuarioRequestDTO;
import com.desafio.modules.conta_api.usuario.dto.AuthContaResponseDTO;

import javax.naming.AuthenticationException;

public interface AuthUsuarioService {

    AuthContaResponseDTO execute(AuthUsuarioRequestDTO authUsuarioRequestDTO) throws AuthenticationException;
}
