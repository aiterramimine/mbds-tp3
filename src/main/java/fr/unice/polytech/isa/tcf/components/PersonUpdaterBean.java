package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.PersonUpdater;
import fr.unice.polytech.isa.tcf.entities.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonUpdaterBean implements PersonUpdater {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void update(Person p) {
        manager.merge(p);
    }
}
