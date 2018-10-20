package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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
}
