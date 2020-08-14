package com.rd.treinamentodev.AvaliacaoSpringBoot.service.BO;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.InstrutorDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.InstrutorEntity;
import org.springframework.stereotype.Component;

@Component
public class InstrutorBO {

    public InstrutorDTO parseToDTO(InstrutorEntity entity){
        InstrutorDTO dto = new InstrutorDTO();

        if (entity == null)
            return dto;

        dto.setNome(entity.getNomeInstrutor());
        dto.setValorHora(entity.getValorHora());
        return dto;
    }
}
