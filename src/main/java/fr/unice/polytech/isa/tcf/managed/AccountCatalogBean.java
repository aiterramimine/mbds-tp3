package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.PersonFinder;
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
    private List<Account> comanagedAccounts;

    @EJB
    private IAccountFinder accountFinder;


    @PostConstruct
    public void init() {
        accounts = new ArrayList<>();

        //ToDo : remove next lines after the session bean is fully implemented
//        accounts = accountFinder.findAll();

        accounts = accountFinder.findAll();
        comanagedAccounts = new ArrayList<>();
        comanagedAccounts = accountFinder.findAllComanagedForUser(151);

        System.out.println(accounts.size() +" ||| " + comanagedAccounts.size());

        //ToDo : retrieve sessions info to call the right method
//        String role = sessionBean.role;
//        if (role.equals("client")) {
//            accounts = finder.findAllForUser(sessionBean.id);w
//        } else if (role.equals("Advisor")) {
//            accounts = finder.findAllForAdvisor(sessionBean.id);
//        } else {
//            // ToDo: Redirect error (no right to access this page)
//        }

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getComanagedAccounts() {
        return comanagedAccounts;
    }

    public void setComanagedAccounts(List<Account> comanagedAccounts) {
        this.comanagedAccounts = comanagedAccounts;
    }

    public String getBalanceStyle(Double balance) {
        if(balance < 0)
            return "decouvert";
        return "creditor";
    }

}
