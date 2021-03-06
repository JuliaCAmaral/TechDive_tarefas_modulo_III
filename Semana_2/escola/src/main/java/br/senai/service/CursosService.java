package br.senai.service;

import br.senai.dao.CursoDAO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Curso;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.*;

@RequestScoped
public class CursosService {

    @Inject
    private CursoDAO cursosDAO;

    public Curso inserir(Curso curso) {
        boolean existente = cursosDAO.find(curso.getCodigo()).isPresent();
        if (existente)
            throw new RegistroExistenteException("Curso", curso.getCodigo());
        cursosDAO.save(curso);
        return curso;
    }

    public void alterar(Curso curso) {
        boolean existente = cursosDAO.find(curso.getCodigo()).isPresent();
        if (!existente)
            throw new RegistroNaoEncontradoException("Curso", curso.getCodigo());
        cursosDAO.update(curso);
    }

    public void excluir(String codigo) {
        boolean existente = cursosDAO.find(codigo).isPresent();
        if (!existente)
            throw new RegistroNaoEncontradoException("Curso", codigo);
        cursosDAO.delete(codigo);
    }

    public List<Curso> obter(String sort, Integer limit) {
        List<Curso> cursos = cursosDAO.findAll();
        if (sort != null) {
            if (sort.equals("assunto"))
                Collections.sort(cursos, Comparator.comparing(Curso::getAssunto));
            else if (sort.equals("duracao"))
                Collections.sort(cursos, Comparator.comparing(Curso::getDuracao));
        }
        if (limit != null && limit < cursos.size())
            cursos = new ArrayList<>( cursos.subList(0, limit) );
        return cursos;
    }

    public Curso obter(String codigo) {
        Optional<Curso> cursoOpt = cursosDAO.find(codigo);
        return cursoOpt.orElseThrow(() -> new RegistroNaoEncontradoException("Curso", codigo));
    }
}
