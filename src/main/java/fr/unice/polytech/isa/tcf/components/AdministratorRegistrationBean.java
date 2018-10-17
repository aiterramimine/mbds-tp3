package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.AdministratorRegistration;
import fr.unice.polytech.isa.tcf.entities.Administrator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorRegistrationBean implements AdministratorRegistration {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public int register(String name, String address) {
        Administrator admin = new Administrator();
        admin.setName(name);
        admin.setAddress(address);

        manager.persist(admin);
        manager.flush();

        return admin.getId();
    }
}
