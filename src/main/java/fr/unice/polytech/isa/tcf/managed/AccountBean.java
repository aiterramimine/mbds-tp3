package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.components.AccountOperationsBean;
import fr.unice.polytech.isa.tcf.components.AccountRegistryBean;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Client;
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
@RequestScoped
public class AccountBean implements Serializable {

    @EJB private IAccountFinder finder;
    @EJB private IAccountRegistry registry;
    @EJB private ClientRegistration clientRegistry;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private Double balance;

    private Integer creditCardNum;

    private String clientName;

    private String clientAddress;

    private Client owner;

    public String select() {
        if(finder.findById(getId()).isPresent()) {
            Account a = finder.findById(getId()).get();
            balance = a.getBalance();
            return "manage?faces-redirect=true&includeViewParams=true";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
            return Signal.UNKOWN;
        }
    }

    public String create() {
        System.out.println("Registering");
        try {
            id = registry.register(getBalance(), getClientName(), getClientAddress());
            return "manage?faces-redirect=true&includeViewParams=true";
        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
            return Signal.UNKOWN;
        }
    }


    public String search() {
        System.out.println("Clicked on the button");
        return "catalog?faces-redirect=true&includeViewParams=true";
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getMinTransfert() {
        return Double.parseDouble(Constants.ACCOUNT_CREATION_MIN_TRANSFER);
    }

    public Integer getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(Integer creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
