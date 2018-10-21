package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private Person connectedUser;
    private String role;
    private String name;

    @EJB
    private PersonFinder personFinder;

    public int getId() {
        return connectedUser.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /*public String logout() {
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        connectedUser = null;
        role = null;
        name = null;
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StringBuffer url = req.getRequestURL();
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
        return base +"user/login.jsf";
    }*/

}
