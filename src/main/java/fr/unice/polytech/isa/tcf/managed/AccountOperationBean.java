package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountCreditor;
import fr.unice.polytech.isa.tcf.IAccountDebitor;
import fr.unice.polytech.isa.tcf.IAccountFinder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class AccountOperationBean implements Serializable {

    @EJB
    private IAccountFinder finder;

    @EJB
    private IAccountCreditor creditor;

    @EJB
    private IAccountDebitor debitor;

    @ManagedProperty("#{accountBean.id}")
    private int id;

    @ManagedProperty("#{accountBean.balance}")
    private double balance;

    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String credit() {
        if(finder.findById(id).isPresent()) {
            creditor.credit(id, amount);
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
