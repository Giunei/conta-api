package com.desafio.modules.conta_api.service;

import com.desafio.modules.conta_api.conta.Situacao;
import com.desafio.modules.conta_api.conta.dto.ContaDTO;
import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public interface ContaService {

    ContaEntity salvar(ContaEntity conta);

    ContaEntity alterarSituacao(Long contaId, Situacao situacao);

    Page<ContaEntity> listarContasPendentesPagamento(OffsetDateTime dataVencimento, String descricao, Pageable pageable);

    Optional<ContaEntity> buscarPorId(Long id);

    BigDecimal buscarValorTotalPagoPorPeriodo(OffsetDateTime dataInicial, OffsetDateTime dataFinal);
}
