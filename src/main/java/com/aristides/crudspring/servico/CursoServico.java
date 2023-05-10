package com.aristides.crudspring.servico;

import com.aristides.crudspring.excecoes.RegistroNotFoundException;
import com.aristides.crudspring.modelo.Curso;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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

    public Curso buscarId(@NotNull @Positive Long id) {
        return cursoRepositorio.findById(id)
                .orElseThrow(
                        () -> new RegistroNotFoundException(id)
                );
    }

    public Curso criarCurso(@Valid Curso novoCurso) {
        return cursoRepositorio.save(novoCurso);
    }

    public Curso atualizarCurso(@Valid @NotNull Long id, Curso curso) {
        return cursoRepositorio.findById(id)
                .map(cursoEncontrado -> {
                    cursoEncontrado.setNome(curso.getNome());
                    cursoEncontrado.setCategoria(curso.getCategoria());
                    return cursoRepositorio.save(cursoEncontrado);
                })
                .orElseThrow(
                        () -> new RegistroNotFoundException(id)
                );
    }

    public void deletarCurso(@Valid @NotNull Long id) {
        cursoRepositorio.delete(cursoRepositorio.findById(id)
                .orElseThrow(
                        () -> new RegistroNotFoundException(id)
                )
        );
    }

}
