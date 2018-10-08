package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.IAccountCreditor;
import fr.unice.polytech.isa.tcf.IAccountDebitor;
import fr.unice.polytech.isa.tcf.IAccountFinder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class AccountOperationBean implements Serializable {

    @EJB
    private IAccountFinder finder;

    @EJB
    private IAccountCreditor creditor;

    @EJB
    private IAccountDebitor debitor;

    @ManagedProperty(value="#{accountBean}")
    private AccountBean accountBean;

    //@ManagedProperty(value="#{accountBean.id}")
    private int id = 22;

    private double amount = 10;

    public int getId() {
        return accountBean.getId();
    }

    public AccountBean getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public void setId(int id) {
        this.id = id;
    }*/

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String credit() {
        if(finder.findById(accountBean.getId()).isPresent()) {
            creditor.credit(accountBean.getId(), amount);
            accountBean.select();
            this.amount = 0;
            return Signal.UNKOWN;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }

    public String debit() {
        if(finder.findById(accountBean.getId()).isPresent()) {
            debitor.debit(accountBean.getId(), amount);
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

}
