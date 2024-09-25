package com.desafio.modules.conta_api.usuario.controller;

import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import com.desafio.modules.conta_api.usuario.UsuarioMapper;
import com.desafio.modules.conta_api.usuario.dto.AuthUsuarioRequestDTO;
import com.desafio.modules.conta_api.usuario.dto.UsuarioDTO;
import com.desafio.modules.conta_api.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Informações do usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    @Operation(summary = "Cadastro de usuário", description = "Essa função é responsável por cadastrar um usuário")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    @ApiResponse(responseCode = "400", description = "Usuário já existe")
    public ResponseEntity<Object> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            var result = usuarioMapper.usuarioEntityToDTO(
                    usuarioService.salvar(usuarioMapper.usuarioDTOToEntity(usuarioDTO)));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
