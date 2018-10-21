package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PersonFinderBean implements PersonFinder {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Person findById(Integer id) throws PersonNotFoundException{
        Query query = manager.createQuery(
                "SELECT p " +
                    "FROM Person p " +
                    "WHERE p.id = :id");
        query.setParameter("id", id);
        List<Person> results = query.getResultList();
        if (results.isEmpty()) {
            throw new PersonNotFoundException("No person with id " + id);
        }
        return results.get(0);
    }

    @Override
    public Person findByName(String name) throws PersonNotFoundException {
        Query query = manager.createQuery(
                "SELECT p " +
                    "FROM Person p " +
                    "WHERE p.name = :name");
        query.setParameter("name", name);
        List<Person> results = query.getResultList();
        if (results.isEmpty()) {
            throw new PersonNotFoundException("No person with name " + name);
        }
        return results.get(0);
    }
}
