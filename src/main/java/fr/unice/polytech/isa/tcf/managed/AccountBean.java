package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.components.AccountOperationsBean;
import fr.unice.polytech.isa.tcf.components.AccountRegistryBean;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class AccountBean implements Serializable {

    @EJB private IAccountFinder finder;
    //@EJB private IAccountRegistry registry;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private int id;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void createAccount() {
        /*try {
            operations.createAccount(new Account(this.getClientName(), this.getBalance()));
        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
        }
        */
    }

    public String select() {
        System.out.println("Entered heeeeere");
        if(finder.findById(getId()).isPresent()) {
            return Signal.SELECTED_ACCOUNT;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Unknown account of id: " + getId()));
            return Signal.UNKOWN;
        }
    }
}
