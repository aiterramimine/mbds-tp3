package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AccountCatalogBean implements Serializable {

    private List<Account> accounts;

    @EJB
    private IAccountFinder finder;

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>();

        accounts = finder.findAll();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getBalanceStyle(Double balance) {
        if(balance < 0)
            return "decouvert";
        return "creditor";
    }

}
