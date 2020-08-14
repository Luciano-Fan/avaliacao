package com.rd.treinamentodev.AvaliacaoSpringBoot.service.BO;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import org.springframework.stereotype.Component;

@Component
public class AlunoBO {

    public AlunoDTO parseToDTO(AlunoEntity entity){
        AlunoDTO dto = new AlunoDTO();

        if (entity == null)
            return dto;

        dto.setNome(entity.getNomeAluno());
        dto.setCpf(entity.getCpf());
        return dto;
    }
}
