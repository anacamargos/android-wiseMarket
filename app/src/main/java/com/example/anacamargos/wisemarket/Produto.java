package com.example.anacamargos.wisemarket;

public class Produto {

    private int id;
    private String nome;
    private double valor;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValor (double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public int getId() {
        return this.id;
    }

    public double getValor () {
        return this.valor;
    }

    public Produto (String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
