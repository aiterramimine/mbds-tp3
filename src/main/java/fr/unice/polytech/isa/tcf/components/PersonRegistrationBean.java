package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdvisorFinder;
import fr.unice.polytech.isa.tcf.PersonRegistration;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PersonRegistrationBean implements PersonRegistration, AdvisorFinder {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public int register(String name, String address) {

        System.out.println("new person : " + name + " || " + address);
        Person p = new Person();
        System.out.println("person created");
        p.setName(name);
        System.out.println("name set");
        p.setAddress(address);

        manager.persist(p);
        manager.flush();

        Integer id = p.getId();



        return id;
    }


    @Override
    public List<Advisor> findAllAdvisors() {
        Query query = manager.createQuery("SELECT c FROM Advisor c");
        return query.getResultList();
    }
}
