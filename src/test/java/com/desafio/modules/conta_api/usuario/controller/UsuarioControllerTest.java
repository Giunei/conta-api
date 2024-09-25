package com.desafio.modules.conta_api.usuario.controller;

import com.desafio.modules.conta_api.conta.dto.ContaDTO;
import com.desafio.modules.conta_api.usuario.UsuarioMapper;
import com.desafio.modules.conta_api.usuario.dto.UsuarioDTO;
import com.desafio.modules.conta_api.usuario.entity.UsuarioEntity;
import com.desafio.modules.conta_api.usuario.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private UsuarioController usuarioController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = standaloneSetup(new UsuarioController(
                usuarioService,
                usuarioMapper))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Nested
    class Dado_um_usuario {

        private UsuarioEntity usuarioSalvo;

        private String json;

        @BeforeEach
        void setup() throws JsonProcessingException {
            usuarioSalvo = UsuarioEntity.builder()
                    .id(1L)
                    .username("João da Silva")
                    .senha("12345678")
                    .build();

            UsuarioDTO usuarioRequisicao = UsuarioDTO.builder()
                    .id(1L)
                    .username("João da Silva")
                    .senha("12345678")
                    .build();

            json = objectMapper.writeValueAsString(usuarioRequisicao);
        }

        @Nested
        class Quando_for_cadastrado {

            private ContaDTO contaBuscada;
            private ResultActions result;

            @BeforeEach
            void setup() throws Exception {
                when(usuarioService.salvar(any())).thenReturn(usuarioSalvo);
                result = mockMvc.perform(post("/usuarios", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
            }

            @Test
            void Entao_deve_trazer_conta_com_os_dados_coretos() throws Exception {
                result.andExpect(jsonPath("$.id").value("1"))
                        .andExpect(jsonPath("$.username").value("João da Silva"))
                        .andReturn();
            }
        }
    }
}