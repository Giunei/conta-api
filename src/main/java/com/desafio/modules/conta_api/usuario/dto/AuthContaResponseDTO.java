package com.desafio.modules.conta_api.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthContaResponseDTO {

    private String tokenAcesso;
    private Long expiraEm;
    private List<String> roles;
}
