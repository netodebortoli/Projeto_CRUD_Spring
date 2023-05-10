package com.aristides.crudspring.excecoes;

public class RegistroNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RegistroNotFoundException(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
