package br.senai.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AlunoDTO implements Serializable {

    @NotNull(message = "Campo obrigatório")
    private Integer matricula;

    @NotEmpty(message = "Campo obrigatório")
    private String nome;

    public AlunoDTO() {
    }

    public AlunoDTO(Integer matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public AlunoDTO(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
