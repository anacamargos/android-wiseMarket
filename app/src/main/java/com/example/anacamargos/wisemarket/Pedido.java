package com.example.anacamargos.wisemarket;

import java.util.Date;

public class Pedido {
    private int id;
    private double valorPedido;
    private String nomePedido;
    private Date dataPedido;


    public Pedido () {}

    public Pedido (double valorPedido, String nomePedido, Date dataPedido) {
        this.valorPedido = valorPedido;
        this.nomePedido = nomePedido;
        this.dataPedido = dataPedido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }
}
