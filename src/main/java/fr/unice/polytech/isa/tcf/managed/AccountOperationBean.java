package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountCreditor;
import fr.unice.polytech.isa.tcf.IAccountDebitor;
import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class AccountOperationBean implements Serializable {

    @EJB
    private IAccountFinder finder;

    @EJB
    private IAccountCreditor creditor;

    @EJB
    private IAccountDebitor debitor;

    private int id;

    private double balance;

    private double amount;

    @PostConstruct
    public void cons() {
           // Gets the id of the account from the view params.
           id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
           updateFromAccount();
    }

    public String credit() {
        if(finder.findById(id).isPresent()) {
            creditor.credit(id, amount);
            updateFromAccount();
           // accountBean.select();
            return Signal.UNKOWN;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }

    public String debit() {
        if(finder.findById(id).isPresent()) {
            debitor.debit(id, amount);
            return Signal.UNKOWN;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }

    public String ret() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return Signal.CREDITED_ACCOUNT;

    }

    public void reload() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {

        }
    }

    /**
     * Updates the fields of the object from the current status of the account in the database.
     */
    public void updateFromAccount() {
        if(finder.findById(getId()).isPresent()) {
            Account a = finder.findById(getId()).get();
            balance = a.getBalance();
        }
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
