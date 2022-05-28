package br.senai.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AlunoDTO implements Serializable {

    @NotNull(message = "Campo obrigatório")
    private Integer matricula;

    @NotEmpty(message = "Campo obrigatório")
    private String Nome;

    public AlunoDTO() {
    }

    public AlunoDTO(Integer matricula, String nome) {
        this.matricula = matricula;
        Nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
