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
        Address ad = new Address(town, address, zipcode);
        manager.persist(ad);

        Administrator admin = new Administrator(name, ad);

        manager.persist(admin);
        manager.flush();

        return admin.getId();
    }
}
