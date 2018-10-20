package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AccountCatalogBean implements Serializable {

    private List<Account> accounts;
    private List<Account> comanagedAccounts;

    @EJB
    private IAccountFinder accountFinder;

    @PostConstruct
    public void init() {
    }

    public List<Account> getAccounts(int id) {
        return accountFinder.findAllForUser(id);
    }

    public List<Account> getAllAccounts() {
        return accountFinder.findAll();
    }

    public List<Account> getAccountsForAdvisor(int id) {
        return accountFinder.findAllForAdvisor(id);
    }

    public List<Account> getComanagedAccounts(int id) {
        return accountFinder.findAllComanagedForUser(id);
    }

    public String getBalanceStyle(Double balance) {
        if(balance < 0)
            return "decouvert";
        return "creditor";
    }

}
