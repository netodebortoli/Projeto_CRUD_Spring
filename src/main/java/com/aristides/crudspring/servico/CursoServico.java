package com.aristides.crudspring.servico;

import com.aristides.crudspring.modelo.Curso;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CursoServico {

    private final CursoRepositorio cursoRepositorio;

    public CursoServico(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public List<Curso> listaCursos() {
        return cursoRepositorio.findAll();
    }

    public Optional<Curso> buscarId(@NotNull @Positive Long id) {
        return cursoRepositorio.findById(id);
    }

    public Curso criarCurso(@Valid Curso novoCurso) {
        return cursoRepositorio.save(novoCurso);
    }

    public Optional<Curso> atualizarCurso(@Valid @NotNull Long id, Curso curso) {
        return cursoRepositorio.findById(id)
                .map(cursoEncontrado -> {
                    cursoEncontrado.setNome(curso.getNome());
                    cursoEncontrado.setCategoria(curso.getCategoria());
                    return cursoRepositorio.save(cursoEncontrado);
                });
    }

    public boolean deletarCurso(@Valid @NotNull Long id) {
        return cursoRepositorio.findById(id)
                .map(cursoEncontrado -> {
                    cursoRepositorio.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

}
