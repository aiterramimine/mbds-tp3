package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdvisorFinder;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Operation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class OperationFinderBean {

    @PersistenceContext
    private EntityManager manager;

    public List<Operation> findAll() {
        Query query = manager.createQuery("SELECT a FROM Operation a");
        return query.getResultList();
    }

    public Operation findById(Integer id) {
        Query query = manager.createQuery("SELECT a FROM Operation a WHERE a.id = :id");
        query.setParameter("id", id);
        List<Operation> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
