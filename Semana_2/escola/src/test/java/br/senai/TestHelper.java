package br.senai;

import br.senai.dto.AlunoDTO;
import br.senai.model.Aluno;
import br.senai.model.Curso;

public class TestHelper {

    public static Curso obterCurso() {
        return new Curso("codigo", "assunto", 0);
    }

    public static Aluno obterAluno() {
        return new Aluno(1, "nome");
    }

    public static AlunoDTO obterAlunoDTO() {
        return new AlunoDTO(1, "nome");
    }
}
