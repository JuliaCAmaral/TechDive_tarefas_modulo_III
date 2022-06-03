package br.senai.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CursoDTO implements Serializable {

    @NotNull(message = "Campo obrigatório")
    private String codigo;

    @NotEmpty(message = "Campo obrigatório")
    private String assunto;

    private Integer duracaoEmDias;

    public CursoDTO() {
    }

    public CursoDTO(String codigo, String assunto, Integer duracaoEmDias) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.duracaoEmDias = duracaoEmDias;
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

    public Integer getDuracaoEmDias() {
        return duracaoEmDias;
    }

    public void setDuracaoEmDias(Integer duracaoEmDias) {
        this.duracaoEmDias = duracaoEmDias;
    }
}
