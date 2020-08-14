package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @PersistenceContext
    private EntityManager em;


    public boolean existeAluno(String cpf) {
        if (em.createNamedQuery("buscarAlunoPorCpf", AlunoEntity.class).setParameter("cpf", cpf).getResultList() != null)
            return true;

        return false;
    }

    public ResponseEntity gravar(AlunoDTO alunoDTO) {
        AlunoEntity entity = new AlunoEntity();
        entity.setNomeAluno(alunoDTO.getNome());
        entity.setCpf(alunoDTO.getCpf());

        //TODO validar se o CPF existe no banco antes de existir, caso exista retornar mensagem de erro
        if (existeAluno(alunoDTO.getCpf()))
            return ResponseEntity.badRequest().body(new ResultData(HttpStatus.BAD_REQUEST.value(),"Campo: cpf informado já existe!"));

        entity = alunoRepository.save(entity);

        ResultData resultData = new ResultData(HttpStatus.CREATED.value(), "Aluno cadastrado com sucesso", entity.getIdAluno());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
    }
}
