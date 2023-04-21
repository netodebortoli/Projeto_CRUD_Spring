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

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criarCurso(@RequestBody Curso novoCurso) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                        .body(cursoRepositorio.save(novoCurso));
        return cursoRepositorio.save(novoCurso);
    }

}
