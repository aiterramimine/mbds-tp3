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
    public int register(String name, String town, String address, int zipcode) {
        Address ad = new Address();
        ad.setTown(town);
        ad.setAddress(address);
        ad.setZipcode(zipcode);

        Advisor advisor = new Advisor();
        advisor.setName(name);
        advisor.setAddress(ad);
        ad.setPerson(advisor);

        manager.persist(ad);
        manager.persist(advisor);
        manager.flush();

        return advisor.getId();
    }

    @Override
    public Advisor registerWithReturn(String name, String town, String address, int zipcode) {
        Address ad = new Address();
        ad.setTown(town);
        ad.setAddress(address);
        ad.setZipcode(zipcode);

        Advisor advisor = new Advisor();
        advisor.setName(name);

        advisor.setAddress(ad);
        ad.setPerson(advisor);

        manager.persist(ad);
        manager.persist(advisor);
        manager.flush();

        return advisor;
    }
}
