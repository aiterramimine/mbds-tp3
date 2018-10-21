package fr.unice.polytech.isa.tcf.managed.person;

import fr.unice.polytech.isa.tcf.AdministratorFinder;
import fr.unice.polytech.isa.tcf.entities.Administrator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class AdminCatalogBean {
    private List<Administrator> administrators;

    @EJB
    private AdministratorFinder administratorFinder;

    public List<Administrator> getAdministrators() {
        return administratorFinder.findAll();
    }
}
