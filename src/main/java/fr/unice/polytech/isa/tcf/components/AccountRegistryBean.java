package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AccountRegistryBean implements IAccountRegistry {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getAccountById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> getAllAccounts() {
        Query query = entityManager.createNamedQuery("Account.findAll");
        System.out.println(query.getResultList());
        return query.getResultList();
    }
}
