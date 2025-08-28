/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.todolistaula.model;

/**
 *
 * @author User
 */
public class Tarefa {
    private int id;
    private String titulo;
    private String descriao;
    private boolean concluida;
    private int usuarioId;

    public Tarefa() {
    }

    public Tarefa(int id, String titulo, String descriao, boolean concluida, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descriao = descriao;
        this.concluida = concluida;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescriao() {
        return descriao;
    }

    public void setDescriao(String descriao) {
        this.descriao = descriao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", titulo=" + titulo + ", descriao=" + descriao + ", concluida=" + concluida + ", usuarioId=" + usuarioId + '}';
    }
}
