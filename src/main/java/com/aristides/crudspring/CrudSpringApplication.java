package com.aristides.crudspring;

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
			c.setCategoria("Front-end");

 			cursoRepositorio.save(c);
		};
	}
}
