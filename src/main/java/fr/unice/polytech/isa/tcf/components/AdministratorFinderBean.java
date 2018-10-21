package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdministratorFinder;
import fr.unice.polytech.isa.tcf.entities.Administrator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AdministratorFinderBean implements AdministratorFinder {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Administrator> findAll() {
        Query query = manager.createQuery("SELECT a FROM Administrator a");
        return query.getResultList();
    }
}
