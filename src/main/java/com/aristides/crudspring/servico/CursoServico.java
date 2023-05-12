package com.aristides.crudspring.servico;

import com.aristides.crudspring.dto.CursoDTO;
import com.aristides.crudspring.dto.Mapper.CursoMapper;
import com.aristides.crudspring.excecoes.RegistroNotFoundException;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CursoServico {

    private final CursoRepositorio cursoRepositorio;
    private final CursoMapper cursoMapper;

    public CursoServico(CursoRepositorio cursoRepositorio, CursoMapper cursoMapper) {
        this.cursoRepositorio = cursoRepositorio;
        this.cursoMapper =  cursoMapper;
    }

    public List<CursoDTO> listaCursos() {
        return cursoRepositorio.findAll()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO buscarId(@NotNull @Positive Long id) {
        return cursoRepositorio.findById(id).map(cursoMapper::toDTO).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public CursoDTO criarCurso(@Valid @NotNull CursoDTO novoCurso) {
        return cursoMapper.toDTO(cursoRepositorio.save(cursoMapper.toEntity(novoCurso)));
    }

    public CursoDTO atualizarCurso(@Valid @NotNull Long id, @Valid @NotNull CursoDTO curso) {
        return cursoRepositorio.findById(id)
                .map(cursoEncontrado -> {
                    cursoEncontrado.setNome(curso.nome());
                    cursoEncontrado.setCategoria(curso.categoria());
                    return cursoMapper.toDTO(cursoRepositorio.save(cursoEncontrado));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void deletarCurso(@Valid @NotNull Long id) {
        cursoRepositorio.delete(cursoRepositorio.findById(id).orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}
