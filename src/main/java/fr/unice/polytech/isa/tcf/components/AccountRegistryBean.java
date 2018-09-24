package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AccountRegistryBean implements IAccountFinder, IAccountRegistry {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void register(String creditCardNum, double initialTransfer) {

    }

    @Override
    public Optional<Account> findById(int id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));

        TypedQuery<Account> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch(NoResultException nre) {
            log.log(Level.FINEST, "No result for ["+id+"]", nre);
            return Optional.empty();
        }
    }
}
