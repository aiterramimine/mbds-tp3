package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IAccountOperations;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountOperationsBean implements IAccountOperations {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private IAccountRegistry register;

    public void deposit(int id, int montant) {
        Account account = register.getAccountById(id);
        account.setBalance(account.getBalance() + montant);
    }

    @Override
    public int withdraw(int id, int amount) {
        Account account = register.getAccountById(id);
        if (amount < account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            return amount;
        } else {
            return 0;
        }
    }

    @Override
    public void createAccount(Account c) {
        entityManager.persist(c);
    }

    @Override
    public void createAccountsTest() {
        createAccount(new Account("John Lennon", 150000));
        createAccount(new Account("Paul McCartney", 950000));
        createAccount(new Account("Ringo Starr", 20000));
        createAccount(new Account("Georges Harrisson", 100000));
    }


}
