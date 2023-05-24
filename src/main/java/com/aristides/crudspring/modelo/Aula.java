package com.aristides.crudspring.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 15, nullable = false, name = "youtube_url")
    private String youtubeURL;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Curso curso;
}
