package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Stateless
public class IClientFinderBean implements IClientFinder {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Client> findByName(String name) {
        Query query = manager.createQuery("SELECT c FROM Client c WHERE c.name = :name");
        query.setParameter("name", name);

        List<Client> results = query.getResultList();
        return Optional.ofNullable(results.get(0));
    }

    @Override
    public List<Client> findAll() {
        Query query = manager.createQuery("SELECT c FROM Client c");

        return query.getResultList();
    }

    @Override
    public List<Client> findAllAdvisorClients(int idAdvisor) {
        Query query = manager.createQuery(
                "SELECT c " +
                    "FROM Client c, Advisor a " +
                    "WHERE a.id = :id " +
                    "AND c MEMBER OF a.clients");
        query.setParameter("id", idAdvisor);

        return query.getResultList();
    }

    @Override
    public Client findById(Integer id) {
        Query query = manager.createQuery("SELECT c FROM Client c WHERE c.id = :id");
        query.setParameter("id", id);
        List<Client> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
