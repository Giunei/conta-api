package com.desafio.modules.conta_api.conta.controller;

import com.desafio.modules.conta_api.conta.ContaMapper;
import com.desafio.modules.conta_api.conta.Situacao;
import com.desafio.modules.conta_api.conta.dto.ContaDTO;
import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import com.desafio.modules.conta_api.exceptions.ContaNotFoundException;
import com.desafio.modules.conta_api.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/contas")
@Tag(name = "Conta", description = "Informações da conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaMapper contaMapper;

    public ContaController(ContaService contaService, ContaMapper contaMapper) {
        this.contaService = contaService;
        this.contaMapper = contaMapper;
    }

    @PostMapping
    @Operation(summary = "Cadastro de conta", description = "Essa função é responsável por cadastrar uma conta")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    @ApiResponse(responseCode = "400", description = "Usuário já existe")
    public ResponseEntity<Object> create(@Valid @RequestBody ContaDTO contaDTO) {
        try {
            var result = contaMapper.contaEntityToDTO(
                    contaService.salvar(contaMapper.contaDTOToEntity(contaDTO)));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualização de conta", description = "Essa função é responsável por atualizar dados de uma conta")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    public ContaDTO atualizar(@PathVariable Long id,
                              @Valid @RequestBody ContaDTO contaDTO) {
        contaDTO.setId(id);
        return contaMapper.contaEntityToDTO(
                contaService.salvar(contaMapper.contaDTOToEntity(contaDTO)));
    }

    @PutMapping(value = "/{id}", params = "situacao")
    @Operation(summary = "Alteração de situação da conta", description = "Essa função é responsável por alterar a situação de uma conta")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    public ContaDTO alterarSituacao(@PathVariable Long id,
                                    @RequestParam Situacao situacao) {
        return contaMapper.contaEntityToDTO(
                contaService.alterarSituacao(id, situacao));
    }

    @GetMapping(params = {"dataVencimento", "descricao"})
    @Operation(summary = "Obter lista de contas a pagar", description = "Essa função retorna uma lista de contas a pagar com filtros de data de vencimento e descrição")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    public Page<ContaDTO> listarContasPendentesPagamento(@RequestParam OffsetDateTime dataVencimento,
                                                         @RequestParam String descricao,
                                                         @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return contaMapper.toPageDTO(
                contaService.listarContasPendentesPagamento(dataVencimento, descricao, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter conta por id", description = "Essa função retorna uma busca de uma conta pelo id")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    public ContaDTO buscarContaPorId(@PathVariable Long id) {
        return contaMapper.contaEntityToDTO(
                contaService.buscarPorId(id).orElseThrow(ContaNotFoundException::new));
    }

    @GetMapping(value = "/total-pago", params = {"dataInicial", "dataFinal"})
    @Operation(summary = "Obter valor total pago por período", description = "Essa função retorna uma busca do valor total pago em determinado intervalo de tempo")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContaEntity.class))
    })
    public BigDecimal calcularValorTotalPagoPorPeriodo(@RequestParam OffsetDateTime dataInicial,
                                                       @RequestParam OffsetDateTime dataFinal) {
        return contaService.buscarValorTotalPagoPorPeriodo(dataInicial, dataFinal);
    }
}
