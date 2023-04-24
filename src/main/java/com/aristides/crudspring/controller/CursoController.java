package com.aristides.crudspring.controller;

import com.aristides.crudspring.model.Curso;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
@AllArgsConstructor
public class CursoController {

    private final CursoRepositorio cursoRepositorio;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Curso> listaCursos() {
        return cursoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarId(@PathVariable Long id) {
        return cursoRepositorio.findById(id)
            .map(registro -> ResponseEntity.ok().body(registro))
            .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criarCurso(@RequestBody Curso novoCurso) {
        return cursoRepositorio.save(novoCurso);
    }
}
