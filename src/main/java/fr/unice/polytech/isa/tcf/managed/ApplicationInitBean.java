package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.AdministratorRegistration;
import fr.unice.polytech.isa.tcf.AdvisorRegistration;
import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.entities.Administrator;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "applicationInitBean", eager = true)
@ApplicationScoped
public class ApplicationInitBean {

    @EJB
    private AdministratorRegistration administratorRegistration;

    @EJB
    private AdvisorRegistration advisorRegistration;

    @EJB
    private ClientRegistration clientRegistration;

    @EJB private IAccountRegistry accountRegistry;

    @PostConstruct
    public void init() {
        administratorRegistration.register("admin", "Antibes");
        Advisor advisor = advisorRegistration.registerWithReturn("advisor", "Nice");
        clientRegistration.register("client", "Sophia-Antipolis", advisor);

    }
}
