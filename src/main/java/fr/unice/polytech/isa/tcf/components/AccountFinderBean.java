package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Person;

import javax.ejb.EJB;
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
public class AccountFinderBean implements IAccountFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<Account> findById(int id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));

        TypedQuery<Account> query = em.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch(NoResultException nre) {
            log.log(Level.FINEST, "No result for ["+id+"]", nre);
            return Optional.empty();
        }
    }

    @Override
    public List<Account> findAll() {

        TypedQuery<Account> query = em.createNamedQuery("findAllAccounts", Account.class);

        return query.getResultList();
    }

    @Override
    public List<Account> findAllForUser(int id) {
        Query query = em.createQuery(
                "SELECT a " +
                        "FROM Account a " +
                        "WHERE a.owner.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Account> findAllForAdvisor(int id) {
        Query query = em.createQuery(
                "SELECT a " +
                    "FROM Account a " +
                    "WHERE a.owner.adviser.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
