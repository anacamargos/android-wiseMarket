package com.example.anacamargos.wisemarket;

import java.util.Date;

public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String cep;
    private String telefone;
    private String email;
    private String numCredito;
    private String datExpira;
    private String codigoSeguranca;
    private String senha;

    public Usuario () {}

    public Usuario (String nome, String cpf, String cep, String telefone, String email, String numCredito, String datExpira,
                    String codigoSeguranca, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this. cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.numCredito = numCredito;
        this.datExpira = datExpira;
        this.codigoSeguranca = codigoSeguranca;
        this.senha = senha;
    }

    public void alterarDados (String nome, String cpf, String cep, String telefone, String email, String numCredito, String datExpira,
                    String codigoSeguranca, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this. cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.numCredito = numCredito;
        this.datExpira = datExpira;
        this.codigoSeguranca = codigoSeguranca;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }
    public String getCep() {
        return cep;
    }
    public String getCpf() {
        return cpf;
    }
    public String getCvv() {
        return codigoSeguranca;
    }
    public String getDatExpira() {
        return datExpira;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getNumCredito() {
        return numCredito;
    }
    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCvv(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setDatExpira(String datExpira) {
        this.datExpira = datExpira;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumCredito(String numCredito) {
        this.numCredito = numCredito;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
