package com.desafio.modules.conta_api.service;

import com.desafio.modules.conta_api.conta.Situacao;
import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import com.desafio.modules.conta_api.exceptions.ContaNotFoundException;
import com.desafio.modules.conta_api.exceptions.UserFoundException;
import com.desafio.modules.conta_api.exceptions.UserNotFoundException;
import com.desafio.modules.conta_api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ContaEntity salvar(ContaEntity conta) {
        return this.contaRepository.save(conta);
    }

    public ContaEntity alterarSituacao(Long contaId, Situacao situacao) {
        ContaEntity conta = this.contaRepository
                .findById(contaId).orElseThrow(ContaNotFoundException::new);

        conta.setSituacao(situacao);
        return contaRepository.save(conta);
    }

    public Page<ContaEntity> listarContasPendentesPagamento(OffsetDateTime dataVencimento, String descricao, Pageable pageable) {
        return contaRepository.findContasAPagarByDataVencimentoAndDescricao(dataVencimento, descricao, pageable);
    }

    public Optional<ContaEntity> buscarPorId(Long id) {
        return contaRepository.findById(id);
    }

    public BigDecimal buscarValorTotalPagoPorPeriodo(OffsetDateTime dataInicial, OffsetDateTime dataFinal) {
        return contaRepository.buscarValorTotalPagoPorPeriodo(dataInicial, dataFinal);
    }
}
