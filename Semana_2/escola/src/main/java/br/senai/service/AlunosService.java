package br.senai.service;

import br.senai.dao.AlunoDAO;
import br.senai.exception.RegistroExistenteException;
import br.senai.exception.RegistroNaoEncontradoException;
import br.senai.model.Aluno;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class AlunosService {

    @Inject
    private AlunoDAO alunosDAO;

    public Aluno inserir(Aluno aluno) {
        boolean existente = alunosDAO.find(aluno.getMatricula()).isPresent();
        if (existente)
            throw new RegistroExistenteException("Aluno", aluno.getMatricula().toString());
        alunosDAO.save(aluno);
        return aluno;
    }

    public void alterar(Aluno aluno) {
        boolean existente = alunosDAO.find(aluno.getMatricula()).isPresent();
        if (!existente)
            throw new RegistroNaoEncontradoException("Aluno", aluno.getMatricula().toString());
        alunosDAO.update(aluno);
    }

    public void excluir(Integer matricula) {
        boolean existente = alunosDAO.find(matricula).isPresent();
        if (!existente)
            throw new RegistroNaoEncontradoException("Aluno", matricula.toString());
        alunosDAO.delete(matricula);
    }

    public List<Aluno> obter(String nomePesquisa) {
        List<Aluno> alunos = alunosDAO.findAll();
        if (nomePesquisa != null)
            alunos = alunos.stream().filter(a -> a.getNome().contains(nomePesquisa)).collect(Collectors.toList());
        return alunos;
    }

    public Aluno obter(Integer matricula) {
        Optional<Aluno> alunoOpt = alunosDAO.find(matricula);
        return alunoOpt.orElseThrow(() -> new RegistroNaoEncontradoException("Aluno", matricula.toString()));
    }
}
