package fr.unice.polytech.isa.tcf.managed.person;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;
import fr.unice.polytech.isa.tcf.managed.AccountBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class PersonOperationBean {

    @EJB
    private AdministratorRegistration administratorRegistration;

    @EJB private AdvisorRegistration advisorRegistration;

    @EJB private ClientRegistration clientRegistration;

    @EJB private IClientFinder clientFinder;

    @EJB private AdvisorFinder advisorFinder;

    @EJB
    private PersonFinder personFinder;

    @EJB
    private PersonUpdater updater;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private String name;

    private Address fullAddress;
    private String town;
    private String address;
    private int zipcode;

    private Person person;

    private String role;

    private String selectedAdvisorId;

    @PostConstruct
    public void cons() {
        // Gets the id of the person from the view params.
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        synchronize();
    }

    private void synchronize() {
        person = null;
        System.out.print("TRY TO FIND ID ");
        System.out.println(id);
        try {
            person = personFinder.findById(id);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ID : " + id);
        name = person.getName();
        System.out.println("name : " + name);
    }

    public String update() {
        person.setName(name);
        person.setAddress(new Address(town, address, zipcode));
        updater.update(person);
        return "./catalogs/client?faces-redirect=true";
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getSelectedAdvisorId() {
        return selectedAdvisorId;
    }

    public void setSelectedAdvisorId(String selectedAdvisorId) {
        this.selectedAdvisorId = selectedAdvisorId;
    }
}
