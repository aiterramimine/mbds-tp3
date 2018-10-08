package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.components.AccountOperationsBean;
import fr.unice.polytech.isa.tcf.components.AccountRegistryBean;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Constants;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean(name="accountBean")
@SessionScoped
public class AccountBean implements Serializable {

    @EJB private IAccountFinder finder;
    @EJB private IAccountRegistry registry;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private int id;
    private double balance;
    private int creditCardNum;

    public double getMinTransfert() {
        return Double.parseDouble(Constants.ACCOUNT_CREATION_MIN_TRANSFER);
    }

    public int getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(int creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String register() {
        System.out.println("Registering");
        try {
            id = registry.register(getBalance());
            return Signal.CREATED_ACCOUNT;
        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
            return Signal.UNKOWN;
        }
    }

    public String select() {
        if(finder.findById(getId()).isPresent()) {
            Account a = finder.findById(getId()).get();
            balance = a.getBalance();
            return Signal.SELECTED_ACCOUNT;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }
}
