package com.aristides.crudspring.dto.Mapper;

import com.aristides.crudspring.dto.CursoDTO;
import com.aristides.crudspring.enumeradores.Categoria;
import com.aristides.crudspring.modelo.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        return new CursoDTO( curso.getId(),curso.getNome(),curso.getCategoria().getValor() );
    }

    public Curso toEntity(CursoDTO cursoDTO) {
        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();

        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria( converterValorCategoria(cursoDTO.categoria()) );
        return curso;
    }

    public Categoria converterValorCategoria(String valor) {
        if (valor == null) {
            return null;
        }
        return switch (valor) {
            case "Front-end" -> Categoria.FRONT_END;
            case "Back-end" -> Categoria.BACK_END;
            default -> throw new IllegalStateException("Categoria inválida: " + valor);
        };
    }

}
