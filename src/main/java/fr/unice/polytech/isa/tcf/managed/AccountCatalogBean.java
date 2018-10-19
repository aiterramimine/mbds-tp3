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

    @EJB
    private IAccountFinder accountFinder;

    @EJB
    private PersonFinder personFinder;

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>();

        //ToDo : remove next line after the session bean is fully implemented
        accounts = accountFinder.findAll();


        //ToDo : retrieve sessions info to call the right method
//        String role = sessionBean.role;
//        if (role.equals("Client")) {
//            accounts = finder.findAllForUser(sessionBean.id);
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

    public String getBalanceStyle(Double balance) {
        if(balance < 0)
            return "decouvert";
        return "creditor";
    }

}
