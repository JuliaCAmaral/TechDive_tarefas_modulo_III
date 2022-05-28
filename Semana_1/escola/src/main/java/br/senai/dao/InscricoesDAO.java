package br.senai.dao;

import br.senai.model.Inscricao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class InscricoesDAO {

    @PersistenceContext(unitName = "ESCOLA")
    EntityManager entityManager;


    public void save(Inscricao inscricao) {
        entityManager.persist(inscricao);
    }

    public List<Inscricao> findAll() {
        return entityManager.createQuery("SELECT i FROM Inscricao i", Inscricao.class).getResultList();
    }

    public Optional<Inscricao> find(Integer id) {
        Inscricao inscricao = entityManager.find(Inscricao.class, id);
        return inscricao != null ? Optional.of(inscricao) : Optional.empty();
    }

    public void delete(Integer id) {
        Inscricao insc = entityManager.find(Inscricao.class, id);
        entityManager.remove(insc);
    }
}
