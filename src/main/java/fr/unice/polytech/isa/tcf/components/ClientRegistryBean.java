package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
public class ClientRegistryBean implements ClientRegistration {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public int register(String name, String town, String address, int zipcode, Advisor advisor) {
        System.out.println("REGISTER USER");

        Address ad = new Address(town, address, zipcode);
        manager.persist(ad);

        Client c = new Client(name, ad);

        c.setAdvisor(advisor);

        advisor.addClient(c);
        //manager.persist(c);

        //advisor.addClient(c);
        manager.merge(advisor);

        //System.out.println(a.getNumClients());

        manager.flush();

        return c.getId();
    }

    @Override
    public Client registerWithReturn(String name, String town, String address, int zipcode, Advisor advisor) {
        System.out.println("REGISTER USER");

        Client c = new Client(name, new Address(town, address, zipcode));
        c.setAdvisor(advisor);
        advisor.addClient(c);

        manager.persist(c);
        manager.merge(advisor);

        manager.flush();

        return c;
    }

}
