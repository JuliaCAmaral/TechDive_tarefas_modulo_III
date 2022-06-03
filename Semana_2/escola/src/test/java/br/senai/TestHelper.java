package br.senai;

import br.senai.model.Curso;

public class TestHelper {

    public static Curso obterCurso() {
        return new Curso("codigo", "assunto", 0);
    }
}
