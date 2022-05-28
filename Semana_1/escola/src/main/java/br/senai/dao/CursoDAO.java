package br.senai.dao;

import br.senai.model.Curso;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class CursoDAO implements Serializable {

    @PersistenceContext(unitName = "ESCOLA")
    EntityManager entityManager;

    public void save(Curso curso) {
        entityManager.persist(curso);
    }

    public List<Curso> findAll() {
        return entityManager.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    public Optional<Curso> find(String codigo) {
        Curso curso = entityManager.find(Curso.class, codigo);
        return curso != null ? Optional.of(curso) : Optional.empty();
    }

    public void delete(String codigo) {
        Curso curso = entityManager.find(Curso.class, codigo);
        entityManager.remove(curso);
    }

    public void update(Curso alterado) {
        Curso curso = entityManager.find(Curso.class, alterado.getCodigo());
        curso.setAssunto(alterado.getAssunto());
        curso.setDuracao(alterado.getDuracao());
        entityManager.merge(curso);
    }
}
