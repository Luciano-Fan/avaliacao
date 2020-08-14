package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.InstrutorDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.TurmaDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.InstrutorEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.TurmaEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.TurmaRepository;
import com.rd.treinamentodev.AvaliacaoSpringBoot.service.BO.AlunoBO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.service.BO.CursoBO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.service.BO.InstrutorBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private CursoBO cursoBO;

    @Autowired
    private AlunoBO alunoBO;

    @Autowired
    private InstrutorBO instrutorBO;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public List<TurmaDTO> listar() {

        List<TurmaDTO> listDTO = new ArrayList<>();

        //TODO implementar a conversão da lista de objetos de TurmaEntity para TurmaDTO e retornar a listDTO preenchida

        for (TurmaEntity turmaEntity : turmaRepository.findAll()) {
            TurmaDTO turmaDTO = new TurmaDTO();

            turmaDTO.setDtFim(SDF.format(turmaEntity.getDtFinal()));
            turmaDTO.setDtInicio(SDF.format(turmaEntity.getDtInicio()));
            turmaDTO.setCurso(cursoBO.parseToDTO(turmaEntity.getCurso()));

            List<InstrutorDTO> listaInstrutores = new ArrayList<>();

            for (InstrutorEntity entity : turmaEntity.getInstrutores()) {
                listaInstrutores.add(instrutorBO.parseToDTO(entity));

                turmaDTO.setInstrutores(listaInstrutores);
            }

            List<AlunoDTO> listaAlunos = new ArrayList<>();

            for (AlunoEntity entity : turmaEntity.getAlunos()) {
                listaAlunos.add(alunoBO.parseToDTO(entity));
                turmaDTO.setAlunos(listaAlunos);
            }

            listDTO.add(turmaDTO);

        }
        return listDTO;
    }
}
