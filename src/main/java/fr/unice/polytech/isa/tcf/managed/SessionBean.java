package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.PersonFinder;
import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private Person connectedUser;
    private String role;

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
                role = "client";
            }
        }
        return role;
    }

    public void connectById(int id) {
        try {
            Person person = personFinder.findById(id);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectByName(String name) {
        try {
            Person person = personFinder.findByName(name);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }

    }
}
