package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdvisorFinder;
import fr.unice.polytech.isa.tcf.entities.Advisor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AdvisorFinderBean implements AdvisorFinder {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Advisor> findAll() {
        Query query = manager.createQuery("SELECT a FROM Advisor a");
        return query.getResultList();
    }

    @Override
    public Advisor findById(int id) {
        Query query = manager.createQuery("SELECT a FROM Advisor a WHERE a.id = :id");
        query.setParameter("id", id);
        List<Advisor> results = query.getResultList();
        System.out.println( results.get(0).getName());
        return results.isEmpty() ? null : results.get(0);
    }
}
