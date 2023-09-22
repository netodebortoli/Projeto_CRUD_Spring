package com.aristides.crudspring;

import com.aristides.crudspring.enumeradores.Categoria;
import com.aristides.crudspring.modelo.Aula;
import com.aristides.crudspring.modelo.Curso;
import com.aristides.crudspring.repositorio.CursoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CursoRepositorio cursoRepositorio) {
		return args -> {
			cursoRepositorio.deleteAll();

			Curso c = new Curso();
			c.setNome("Angular");
			c.setCategoria(Categoria.FRONT_END);

			Aula a = new Aula();
			a.setNome("Introdução");
			a.setYoutubeUrl("watch?v=01");
			a.setCurso(c);
			c.getAulas().add(a);

			Aula a2 = new Aula();
			a2.setNome("Hello World");
			a2.setYoutubeUrl("watch?v=02");
			a2.setCurso(c);
			c.getAulas().add(a2);

			cursoRepositorio.save(c);
		};
	}
}
