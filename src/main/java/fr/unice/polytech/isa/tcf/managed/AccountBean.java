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
import java.util.ArrayList;
import java.util.List;
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

    private List<Client> coOwners;

    private Integer selectedCoOwnerId;

    private Boolean hideOwnerCreation;

    private Boolean hasOwner;

    private Boolean hideOwnerSelection;

    private Integer selectedOwnerId;

    private Boolean hideCoOwnerSelection;

    private Boolean hasCoOwners;

    @PostConstruct
    public void init() {
        owner = new Client();
        hasOwner = false;
        hasCoOwners = false;
        hideOwnerSelection = true;
        hideCoOwnerSelection = true;
        hideOwnerCreation = true;

        coOwners = new ArrayList<>();
    }

    public String create() {
        System.out.println("Registering");
        try {
            if(getBalance() >= 50) {
                id = registry.register(getBalance(), getOwner(), coOwners);
                return "manage?faces-redirect=true&includeViewParams=true";

            }

            FacesContext.getCurrentInstance()
                    .addMessage("form-success",
                            new FacesMessage("Depot minimal est de 50 Euro"));
            hasOwner = false;
            return null;

        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
            return Signal.UNKOWN;
        }
    }

    public String manage(Integer id) {
        this.id = id;
        return "manage?faces-redirect=true&includeViewParams=true";
    }

    public void updateOwner() {
        // System.out.println(owner.getName());
        hideOwnerCreation = true;
        hasOwner = true;
    }

    public void updateOwnerAfterSelection() {
        owner = clientFinder.findById(selectedOwnerId);
        hideOwnerSelection = true;
        hasOwner = true;
    }

    public void updateCoOwnerAfterSelection() {
        Client newCoOwner = clientFinder.findById(selectedCoOwnerId);
        coOwners.add(newCoOwner);

        hideCoOwnerSelection = true;
    }

    public String search() {
        System.out.println("Clicked on the button");
        return "catalog?faces-redirect=true&includeViewParams=true";
    }

    public void createOwner() {
        hideOwnerCreation = false;

        hideOwnerSelection = true;
        hideCoOwnerSelection = true;
    }

    public void selectOwner() {
        hideOwnerSelection = false;

        hideOwnerCreation = true;
        hideCoOwnerSelection = true;
    }

    public void selectCoOwner() {
        hideCoOwnerSelection = false;

        hideOwnerSelection = true;
        hideOwnerCreation = true;
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

    public Boolean getHideCoOwnerSelection() {
        return hideCoOwnerSelection;
    }

    public void setHideCoOwnerSelection(Boolean hideCoOwnerSelection) {
        this.hideCoOwnerSelection = hideCoOwnerSelection;
    }

    public Boolean getHasCoOwners() {
        return hasCoOwners;
    }

    public void setHasCoOwners(Boolean hasCoOwners) {
        this.hasCoOwners = hasCoOwners;
    }

    public Integer getSelectedCoOwnerId() {
        return selectedCoOwnerId;
    }

    public void setSelectedCoOwnerId(Integer selectedCoOwnerId) {
        this.selectedCoOwnerId = selectedCoOwnerId;
    }

    public List<Client> getCoOwners() {
        return coOwners;
    }

    public void setCoOwners(List<Client> coOwners) {
        this.coOwners = coOwners;
    }
}