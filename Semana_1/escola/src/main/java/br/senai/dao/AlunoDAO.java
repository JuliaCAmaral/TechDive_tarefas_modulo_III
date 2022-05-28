package br.senai.dao;

import br.senai.model.Aluno;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class AlunoDAO implements Serializable {

    @PersistenceContext(unitName = "ESCOLA")
    EntityManager entityManager;

    public void save(Aluno aluno) {
        entityManager.persist(aluno);
    }

    public List<Aluno> findAll() {
        return entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    public Optional<Aluno> find(Integer matricula) {
        Aluno aluno = entityManager.find(Aluno.class, matricula);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    public void delete(Integer matricula) {
        Aluno aluno = entityManager.find(Aluno.class, matricula);
        entityManager.remove(aluno);
    }

    public void update(Aluno alterado) {
        Aluno aluno = entityManager.find(Aluno.class, alterado.getMatricula());
        aluno.setNome(alterado.getNome());
        entityManager.merge(aluno);
    }
}
