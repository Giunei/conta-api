package com.desafio.modules.conta_api.conta.dto;

import com.desafio.modules.conta_api.conta.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    private Long id;
    private OffsetDateTime dataVencimento;
    private OffsetDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private Situacao situacao;
}
