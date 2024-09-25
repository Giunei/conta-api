package com.desafio.modules.conta_api.usuario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Long id;
    private String username;
    private String senha;
}
