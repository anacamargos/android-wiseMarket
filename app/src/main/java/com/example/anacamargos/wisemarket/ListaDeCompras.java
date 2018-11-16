package com.example.anacamargos.wisemarket;

public class ListaDeCompras {

    private int id;
    private String nomeLista;

    public ListaDeCompras () {}

    public ListaDeCompras (String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
}
