package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountCreditor;
import fr.unice.polytech.isa.tcf.IAccountDebitor;
import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
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

    private Double amount;

    @PostConstruct
    public void cons() {
           // Gets the id of the account from the view params.
           id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
           synchronizeWithAccount();
    }

    public String credit() {

        if(amount == null) {
            FacesContext.getCurrentInstance()
                    .addMessage("from-error",
                            new FacesMessage("Veuillez entrer le montant afin de créditer le compte"));
            return null;
        }

        if(finder.findById(id).isPresent()) {
            creditor.credit(id, amount);
            synchronizeWithAccount();
            FacesContext.getCurrentInstance()
                    .addMessage("form-success",
                            new FacesMessage("Le montant " + getAmount() + " Euros a été crédité" +
                                    "sur votre compte avec succès"));
            setAmount(null);
            return null;
        }

        FacesContext.getCurrentInstance()
                .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
        return null;

    }

    public String debit() {
        if(finder.findById(id).isPresent()) {
            debitor.debit(id, amount);
            setAmount(null);
            return Signal.UNKOWN;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }

    public String previousPage() {
        return "index?faces-redirect=true";

    }

    /**
     * Updates the fields of the object from the current status of the account in the database.
     */
    public void synchronizeWithAccount() {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
