package com.desafio.modules.conta_api.usuario.service;

import com.desafio.modules.conta_api.usuario.dto.UsuarioDTO;
import com.desafio.modules.conta_api.usuario.entity.UsuarioEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UsuarioServiceImplTest {


    @Autowired
    private UsuarioService usuarioService;

    @Nested
    class Dado_um_usuario {

        private UsuarioEntity usuario;

        @BeforeEach
        void setup() {
            usuario = UsuarioEntity.builder()
                    .id(1L)
                    .username("João da Silva")
                    .senha("12345678")
                    .build();
        }

        @Nested
        class Quando_for_cadastrado {

            private UsuarioEntity usuarioSalvo;

            @BeforeEach
            void setup() {
                usuarioSalvo = usuarioService.salvar(usuario);
            }

            @Test
            void Entao_deve_trazer_conta_com_os_dados_coretos() throws Exception {
                assertNotNull(usuarioSalvo);
                assertEquals("João da Silva", usuario.getUsername());
            }
        }
    }
}