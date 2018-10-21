package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.components.AccountOperationsBean;
import fr.unice.polytech.isa.tcf.components.AccountRegistryBean;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.entities.Constants;
import fr.unice.polytech.isa.tcf.managed.person.PersonBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean(name="accountBean")
@ViewScoped
public class AccountBean implements Serializable {

    @EJB private IAccountFinder finder;
    @EJB private IAccountRegistry registry;
    @EJB private ClientRegistration clientRegistry;

    @EJB private IClientFinder clientFinder;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private Double balance;

    private Integer creditCardNum;

    private String clientName;

    private String clientAddress;

    private Client owner;

    private Boolean hideOwnerCreation;

    private Boolean hasOwner;

    private Boolean hideOwnerSelection;

    private Integer selectedOwnerId;

    @PostConstruct
    public void init() {
        owner = new Client();
        hideOwnerCreation = true;
        hasOwner = false;
        hideOwnerSelection = true;
    }

    public String create() {
        System.out.println("Registering");
        try {
            //id = registry.register(getBalance(), getClientName(), getClientAddress());
            id = registry.register(getBalance(), getOwner());
            return "manage?faces-redirect=true&includeViewParams=true";
        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
            return Signal.UNKOWN;
        }
    }

    public void updateOwner() {
       // System.out.println(owner.getName());
        hideOwnerCreation = true;
        hasOwner = true;
    }

    public void updateOwnerAfterSelection() {
        System.out.println(selectedOwnerId);
        owner = clientFinder.findById(selectedOwnerId);
        System.out.println(owner.getName());
        hideOwnerSelection = true;
        hasOwner = true;

    }

    public String search() {
        System.out.println("Clicked on the button");
        return "catalog?faces-redirect=true&includeViewParams=true";
    }

    public void createOwner() {
        hideOwnerCreation = false;
    }

    public void selectOwner() {
        hideOwnerCreation = true;
        hideOwnerSelection = false;
    }

    public void setCoOwner() {
        System.out.println("Setting coowner");
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

    public Boolean getHideOwnerCreation() {
        return hideOwnerCreation;
    }

    public void setHideOwnerCreation(Boolean hideOwnerCreation) {
        this.hideOwnerCreation = hideOwnerCreation;
    }

    public Boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(Boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public Boolean getHideOwnerSelection() {
        return hideOwnerSelection;
    }

    public void setHideOwnerSelection(Boolean hideOwnerSelection) {
        this.hideOwnerSelection = hideOwnerSelection;
    }

    public Integer getSelectedOwnerId() {
        return selectedOwnerId;
    }

    public void setSelectedOwnerId(Integer selectedOwnerId) {
        this.selectedOwnerId = selectedOwnerId;
    }
}
