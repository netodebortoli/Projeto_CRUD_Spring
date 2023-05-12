package com.aristides.crudspring.dto.Mapper;

import com.aristides.crudspring.dto.CursoDTO;
import com.aristides.crudspring.modelo.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(),curso.getNome(),curso.getCategoria() );
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();

        if ( cursoDTO.id() != null ) {
            curso.setId(cursoDTO.id());
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria(cursoDTO.categoria());
        return curso;
    }
}
