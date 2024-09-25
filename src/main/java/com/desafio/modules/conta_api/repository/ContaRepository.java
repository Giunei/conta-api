package com.desafio.modules.conta_api.repository;

import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    @Query("SELECT c FROM conta c " +
            "WHERE c.dataVencimento <= :dataVencimento " +
            "AND c.dataPagamento IS NULL " +
            "AND LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    Page<ContaEntity> findContasAPagarByDataVencimentoAndDescricao(
            @Param("dataVencimento") OffsetDateTime dataVencimento,
            @Param("descricao") String descricao,
            Pageable pageable);

    @Query("SELECT SUM(c.valor) FROM conta c " +
            "WHERE c.dataPagamento BETWEEN :dataInicial AND :dataFinal")
    BigDecimal buscarValorTotalPagoPorPeriodo(@Param("dataInicial") OffsetDateTime dataInicial, @Param("dataFinal") OffsetDateTime dataFinal);
}
