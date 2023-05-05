package com.aristides.crudspring.controladora;

import com.aristides.crudspring.modelo.Curso;
import com.aristides.crudspring.servico.CursoServico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    private final CursoServico cursoServico;

    public CursoController(CursoServico cursoServico) {
        this.cursoServico = cursoServico;
    }

    @GetMapping
    public List<Curso> listaCursos() {
        return cursoServico.listaCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarId(@PathVariable @NotNull @Positive Long id) {
        return cursoServico.buscarId(id)
            .map(registro -> ResponseEntity.ok().body(registro))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criarCurso(@RequestBody @Valid Curso novoCurso) {
        return cursoServico.criarCurso(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable @Valid @NotNull Long id, @RequestBody Curso curso) {
        return cursoServico.atualizarCurso(id, curso)
                .map(cursoEncontrado -> ResponseEntity.ok().body(cursoEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable @Valid @NotNull Long id) {
        if (cursoServico.deletarCurso(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
}
