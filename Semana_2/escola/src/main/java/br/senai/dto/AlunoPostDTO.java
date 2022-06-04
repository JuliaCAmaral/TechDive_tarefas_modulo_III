package br.senai.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AlunoPostDTO implements Serializable {

    @NotEmpty(message = "Campo obrigatório")
    private String nome;

    public AlunoPostDTO() {
    }

    public AlunoPostDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
