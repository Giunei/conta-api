package com.desafio.modules.conta_api.conta;

import com.desafio.modules.conta_api.conta.dto.ContaDTO;
import com.desafio.modules.conta_api.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {

    public ContaEntity contaDTOToEntity(ContaDTO contaDTO) {
        return ContaEntity.builder()
                .id(contaDTO.getId())
                .dataVencimento(contaDTO.getDataVencimento())
                .dataPagamento(contaDTO.getDataPagamento())
                .valor(contaDTO.getValor())
                .descricao(contaDTO.getDescricao())
                .situacao(contaDTO.getSituacao())
                .build();
    }

    public ContaDTO contaEntityToDTO(ContaEntity contaEntity) {
        return ContaDTO.builder()
                .id(contaEntity.getId())
                .dataVencimento(contaEntity.getDataVencimento())
                .dataPagamento(contaEntity.getDataPagamento())
                .valor(contaEntity.getValor())
                .descricao(contaEntity.getDescricao())
                .situacao(contaEntity.getSituacao())
                .build();
    }

    public Page<ContaDTO> toPageDTO(Page<ContaEntity> contas) {
        return contas.map(this::contaEntityToDTO);
    }
}
