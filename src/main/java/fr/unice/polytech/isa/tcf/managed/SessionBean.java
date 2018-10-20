package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private Person connectedUser;
    private String role;
    private String name;
    private String error;

    @EJB
    private PersonFinder personFinder;

    public int getId() {
        return connectedUser.getId();
    }

    public String getRole() {
        if (role.length() == 0) {
            if (connectedUser.isAnAdministrator()) {
                role = "Administrator";
            } else if (connectedUser.isAnAdvisor()) {
                role = "Advisor";
            } else {
                role = "Client";
            }
        }
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public Person getConnectedUser() {
        return connectedUser;
    }

    public String login() {
        Person person = null;
        try {
            person = personFinder.findByName(this.name);
        } catch (PersonNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage("form-error", new FacesMessage("No user with this username"));

            return "./login.jsf";
        }

        connectedUser = person;
        return "../accounts/index?faces-redirect=true&includeViewParams=true";

    }

}
