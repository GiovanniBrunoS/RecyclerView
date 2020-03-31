package com.example.recyclerview;

import java.io.Serializable;

public class Compras implements Serializable {
    private String nome;
    private String marca;
    private int quantidade;

    public Compras(){
    }

    public Compras(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Compras(String nome, String marca, int quantidade) {
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

