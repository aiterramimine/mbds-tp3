package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdvisorRegistration;
import fr.unice.polytech.isa.tcf.entities.Advisor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdvisorRegistrationBean implements AdvisorRegistration {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public int register(String name, String address) {
        Advisor advisor = new Advisor(name, address);

        manager.persist(advisor);
        manager.flush();

        return advisor.getId();
    }

    @Override
    public Advisor registerWithReturn(String name, String address) {
        Advisor advisor = new Advisor(name, address);

        manager.persist(advisor);
        manager.flush();

        return advisor;
    }
}
