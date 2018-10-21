package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdministratorRegistration;
import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Administrator;
import org.apache.openejb.jee.was.v6.xmi.Add;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorRegistrationBean implements AdministratorRegistration {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public int register(String name, String town, String address, int zipcode) {
        Address ad = new Address();
        ad.setTown(town);
        ad.setAddress(address);
        ad.setZipcode(zipcode);

        Administrator admin = new Administrator();
        admin.setName(name);
        admin.setAddress(ad);
        ad.setPerson(admin);

        manager.persist(ad);
        manager.persist(admin);
        manager.flush();

        return admin.getId();
    }
}
