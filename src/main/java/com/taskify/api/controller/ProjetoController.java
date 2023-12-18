package com.taskify.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskify.api.model.Projeto;
import com.taskify.api.repository.ProjetoRepository;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {

    @PostMapping
    public ResponseEntity<Projeto> cadastrarProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projetoRepository.save(projeto));
    }

    @GetMapping
    public ResponseEntity<Page<Projeto>> listarProjetos(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projetoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Projeto>> buscarProjeto(@PathVariable("id") Long idProjeto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projetoRepository.findById(idProjeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(
            @PathVariable("id") Long idProjeto,
            @RequestBody Projeto projeto) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(idProjeto);

        if (projetoExistente.isPresent()) {
            projetoExistente.get().setNome(projeto.getNome());
            projetoExistente.get().setDescricao(projeto.getDescricao());
            projetoExistente.get().setResponsavelTecnico(projeto.getResponsavelTecnico());
            projetoExistente.get().setParticipantes(projeto.getParticipantes());

            return ResponseEntity.status(HttpStatus.OK)
                        .body(projetoRepository.save(projetoExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProjeto(@PathVariable("id") Long idProjeto) {
        projetoRepository.deleteById(idProjeto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Projeto deletado com sucesso!");
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<List<Projeto>>> filtrarProjetosPorResponsavelTecnico(
            @PathVariable("id") Long idUsuario) {
        Optional<List<Projeto>> projetosExistentes = projetoRepository.findByResponsavelTecnico(idUsuario);

        if (projetosExistentes.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(projetosExistentes);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Autowired
    private ProjetoRepository projetoRepository;

}
