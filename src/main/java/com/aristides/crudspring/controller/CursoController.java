package com.aristides.crudspring.controller;

import com.aristides.crudspring.model.Curso;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
@AllArgsConstructor
public class CursoController {

    private final CursoRepositorio cursoRepositorio;

    @GetMapping
    public List<Curso> listaCursos() {
        return cursoRepositorio.findAll();
    }
}
