package com.aristides.crudspring.enumeradores;

public enum Categoria {
    BACK_END("Back-end"),
    FRONT_END("Front-end");
    private String valor;

    private Categoria(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
