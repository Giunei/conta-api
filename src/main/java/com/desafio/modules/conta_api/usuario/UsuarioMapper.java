package com.desafio.modules.conta_api.usuario;

import com.desafio.modules.conta_api.conta.dto.ContaDTO;
import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import com.desafio.modules.conta_api.usuario.dto.AuthUsuarioRequestDTO;
import com.desafio.modules.conta_api.usuario.dto.UsuarioDTO;
import com.desafio.modules.conta_api.usuario.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioEntity usuarioDTOToEntity(UsuarioDTO user) {
        return UsuarioEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .senha(user.getSenha())
                .build();
    }

    public UsuarioDTO usuarioEntityToDTO(UsuarioEntity usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .senha(usuario.getSenha())
                .build();
    }
}
