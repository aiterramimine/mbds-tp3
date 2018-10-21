package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdvisorRegistration;
import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import net.bootsfaces.render.A;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdvisorRegistrationBean implements AdvisorRegistration {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public int register(String name, String town, String street, int zipcode) {
        Address ad = new Address(town, street, zipcode);
        manager.persist(ad);

        Advisor advisor = new Advisor(name, ad);

        System.out.println("ADVISOR ADDRESS :::::");
        System.out.println(advisor.getAddress().getAddress());

        manager.persist(advisor);
        manager.flush();

        return advisor.getId();
    }

    @Override
    public Advisor registerWithReturn(String name, String town, String address, int zipcode) {
        Address ad = new Address(town, address, zipcode);
        manager.persist(ad);

        Advisor advisor = new Advisor(name, ad);

        manager.persist(advisor);
        manager.flush();

        return advisor;
    }
}
