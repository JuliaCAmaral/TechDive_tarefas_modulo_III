package br.senai.model;

import javax.persistence.*;

@Entity
@Table(name = "CURSOS")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codigo;

    private String assunto;
    private Integer duracao; //em dias

    public Curso() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}
