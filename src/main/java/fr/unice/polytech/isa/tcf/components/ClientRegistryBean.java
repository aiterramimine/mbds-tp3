package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.ClientFinder;
import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Optional;
import java.util.logging.Logger;

public class ClientRegistryBean implements ClientFinder, ClientRegistration {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext private EntityManager manager;

    @Override
    public void register(String name, String address, String creditCardNum, double initialTransfer) throws AlreadyExistingClientException {

        if(findByName(name).isPresent()) {
            throw new AlreadyExistingClientException(name);
        }

        Client c = new Client(name, address);
        c.setCreditCardNum(creditCardNum);
        c.setInitialTransfer(initialTransfer);

        manager.persist(c);

    }

    @Override
    public Optional<Client> findByName(String name) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = builder.createQuery(Client.class);


        return null;
    }

}
