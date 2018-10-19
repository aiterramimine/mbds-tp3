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
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class ClientRegistryBean implements ClientFinder, ClientRegistration {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public void register(String name, String address, String creditCardNum, double initialTransfer) throws AlreadyExistingClientException {

        if(findByName(name).isPresent()) {
            throw new AlreadyExistingClientException(name);
        }

        Client c = new Client(name, address);

        manager.persist(c);

    }

    @Override
    public int register(String name, String address, Advisor advisor) {
        System.out.println("REGISTER USER");

        Client c = new Client(name, address);
        c.setAdvisor(advisor);

        manager.persist(c);

        return c.getId();
    }

    @Override
    public Optional<Client> findByName(String name) {
        Query query = manager.createQuery("SELECT c FROM Client c WHERE c.name = :name");
        query.setParameter("name", name);

        List<Client> results = query.getResultList();
        return Optional.ofNullable(results.get(0));
    }

}
