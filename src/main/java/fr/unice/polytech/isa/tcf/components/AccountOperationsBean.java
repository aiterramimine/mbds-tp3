package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class AccountOperationsBean implements IAccountCreditor, IAccountDebitor {

    @PersistenceContext
    private EntityManager entityManager;

    private double id;

    private double balance;

    @EJB
    private IAccountFinder finder;

    @Override
    public void credit(int accountId, double amount) {
        Optional<Account> optionalAccount = finder.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance() + amount);
        }
    }

    @Override
    public double debit(int accountId, double amount) {
        Optional<Account> optionalAccount = finder.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (amount < account.getBalance()) {
                account.setBalance(account.getBalance() - amount);
                return amount;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

//    public void deposit(int id, int montant) {
//       /* Account account = register.getAccountById(id);
//        account.setBalance(account.getBalance() + montant);*/
//    }
//
//    @Override
//    public int withdraw(int id, int amount) {
//       /* Account account = register.getAccountById(id);
//        if (amount < account.getBalance()) {
//            account.setBalance(account.getBalance() - amount);
//            return amount;
//        } else {*/
//            return 0;
//        //}
//    }
//
//    @Override
//    public void createAccount(Account c) {
//        entityManager.persist(c);
//    }
//
//    @Override
//    public void createAccountsTest() {
//        createAccount(new Account("John Lennon", 150000));
//        createAccount(new Account("Paul McCartney", 950000));
//        createAccount(new Account("Ringo Starr", 20000));
//        createAccount(new Account("Georges Harrisson", 100000));
//    }


}
