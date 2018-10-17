package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.PersonRegistration;
import fr.unice.polytech.isa.tcf.entities.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean(name="personBean")
@RequestScoped
public class PersonBean implements Serializable {

    //@EJB private IAccountFinder finder;
    @EJB private PersonRegistration registry;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private String name;

    private String address;

    private boolean isClient;

    private boolean isAdvisor;

    private boolean isAdmin;

    private Integer advisorID;

//    public String select() {
//        if(finder.findById(getId()).isPresent()) {
//            Account a = finder.findById(getId()).get();
//            balance = a.getBalance();
//            return "manage?faces-redirect=true&includeViewParams=true";
//        } else {
//            FacesContext.getCurrentInstance()
//                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
//            return Signal.UNKOWN;
//        }
//    }

    public String create() {
        System.out.println("Registering");
        try {
            id = registry.register(getName(), getAddress());
            System.out.println("registered");
            return "../accounts/index?faces-redirect=true&includeViewParams=true";
        } catch (Exception e) {
            System.out.print("error creating person : ");
            e.printStackTrace();
            return "../accounts/index?faces-redirect=true&includeViewParams=true";
        }
    }


    public String search() {
        System.out.println("Clicked on the button");
        return "catalog?faces-redirect=true&includeViewParams=true";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdvisorID(Integer advisorID) {
        this.advisorID = advisorID;
    }

    public Object getAdvisorID() {
        return advisorID;
    }
}
