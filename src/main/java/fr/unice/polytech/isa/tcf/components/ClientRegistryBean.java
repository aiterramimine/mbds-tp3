package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.ClientFinder;
import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class ClientRegistryBean implements ClientRegistration {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public int register(String name, String address, Advisor advisor) {
        System.out.println("REGISTER USER");

        Client c = new Client(name, address);
        advisor.addClient(c);
//        c.setAdvisor(advisor);

        manager.persist(c);
        manager.merge(advisor);

        manager.flush();

        return c.getId();
    }

    @Override
    public Client registerWithReturn(String name, String address, Advisor advisor) {
        System.out.println("REGISTER USER");

        Client c = new Client(name, address);
        advisor.addClient(c);
//        c.setAdvisor(advisor);

        manager.persist(c);
        manager.merge(advisor);

        manager.flush();

        return c;
    }

}
