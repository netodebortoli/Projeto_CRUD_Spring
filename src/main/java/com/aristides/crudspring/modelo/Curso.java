package com.aristides.crudspring.modelo;

import com.aristides.crudspring.enumeradores.Categoria;
import com.aristides.crudspring.enumeradores.Status;
import com.aristides.crudspring.enumeradores.conversores.ConversorCategoria;
import com.aristides.crudspring.enumeradores.conversores.ConversorStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE Curso SET status = 'Inativo' WHERE id = ? ")
@Where(clause = "status = 'Ativo'")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = ConversorCategoria.class)
    private Categoria categoria;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = ConversorStatus.class)
    private Status status = Status.ATIVO;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "curso")
    private List<Aula> aulas = new ArrayList();
}
