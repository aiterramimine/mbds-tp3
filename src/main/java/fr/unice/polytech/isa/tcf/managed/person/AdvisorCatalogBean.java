package fr.unice.polytech.isa.tcf.managed.person;

import fr.unice.polytech.isa.tcf.AdvisorFinder;
import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AdvisorCatalogBean {
    private List<Advisor> advisors;

    @EJB
    private AdvisorFinder advisorFinder;

    @PostConstruct
    public void init() {
        advisors = new ArrayList<>();
        advisors = advisorFinder.findAll();
//        System.out.println(advisors);
    }

    public void setAdvisors(List<Advisor> clients) {
        this.advisors = clients;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }


}
