package br.senai;

import br.senai.dto.AlunoDTO;
import br.senai.dto.AlunoPostDTO;
import br.senai.dto.CursoDTO;
import br.senai.model.Aluno;
import br.senai.model.Curso;
import br.senai.model.Inscricao;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    public static Curso obterCurso() {
        return new Curso("codigo", "assunto", 1);
    }

    public static CursoDTO obterCursoDTO() {
        return new CursoDTO("codigo", "assunto", 1);
    }

    public static Aluno obterAluno() {
        return new Aluno(1, "nome");
    }

    public static AlunoDTO obterAlunoDTO() {
        return new AlunoDTO(1, "nome");
    }

    public static AlunoPostDTO obterAlunoPostDTO() {
        return new AlunoPostDTO("Felipe");
    }

    public static Inscricao obterInscricao() {
        return new Inscricao(1, obterAluno(), obterCurso());
    }

    public static List<Inscricao> obterInscricoes() {
        List<Inscricao> inscricoes = new ArrayList<>();
        inscricoes.add(obterInscricao());
        return inscricoes;
    }
}
