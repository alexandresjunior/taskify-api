package com.taskify.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskify.api.model.Usuario;
import com.taskify.api.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(
        @PageableDefault(size = 10, page = 1, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuario(@PathVariable("id") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioRepository.findById(idUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Usuário deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable("id") Long idUsuario,
            @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);

        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Usuario>> buscarUsuarioPeloEmail(
            @RequestParam("email") String email) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
        
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

}
