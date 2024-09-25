package com.desafio.modules.conta_api.conta.entity;

import com.desafio.modules.conta_api.conta.Situacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "conta")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento", nullable = false)
    private OffsetDateTime dataVencimento;

    @Column(name = "data_pagamento")
    private OffsetDateTime dataPagamento;

    private BigDecimal valor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;
}
