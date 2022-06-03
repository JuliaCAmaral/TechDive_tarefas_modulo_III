package br.senai.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CURSOS")
public class Curso {

    @Id
    @Column(name = "COD_CURSO")
    private String codigo;
    private String assunto;
    private Integer duracao;

    public Curso() {
    }

    public Curso(String codigo, String assunto, Integer duracao) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.duracao = duracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return codigo.equals(curso.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
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
