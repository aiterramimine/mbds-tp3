package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.PersonRegistration;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingPersonException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class PersonRegistrationBean implements PersonRegistration {
    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public int register(String name, String address) {

        Person p = new Person();
        p.setName(name);
        p.setAddress(address);

        manager.persist(p);
        manager.flush();

        //Integer id = p.getId();



        return p.getId();
    }



}
