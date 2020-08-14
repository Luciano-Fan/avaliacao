package com.rd.treinamentodev.AvaliacaoSpringBoot.controller;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/curso")
    public ResponseEntity gravar(@RequestBody CursoDTO cursoDTO){
        ResultData resultData = null;

        if(cursoDTO.getNome() == null)
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Campo: nome não informado!");

        else if(cursoDTO.getCargaHoraria() == null)
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Campo: carga horária não informado!");

        if (resultData != null)
            return ResponseEntity.badRequest().body(resultData);

        return cursoService.gravar(cursoDTO);
    }

    @GetMapping("/curso/{nome}")
    public ResponseEntity buscarPorNome (@PathVariable("nome") String nome){

        return ResponseEntity.ok().body(cursoService.buscarPorNome(nome));
    }
}
