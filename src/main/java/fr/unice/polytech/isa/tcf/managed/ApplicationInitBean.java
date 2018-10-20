package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.entities.Administrator;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLOutput;

@ManagedBean(name = "applicationInitBean", eager = true)
@ApplicationScoped
public class ApplicationInitBean {

    @EJB
    private AdministratorRegistration administratorRegistration;

    @EJB
    private AdvisorRegistration advisorRegistration;

    @EJB
    private AdvisorFinder advisorFinder;

    @EJB
    private ClientRegistration clientRegistration;

    @EJB private IAccountRegistry accountRegistry;

    @PostConstruct
    public void init() {
        administratorRegistration.register("admin", "Antibes");
        Advisor advisor = advisorRegistration.registerWithReturn("advisor", "Nice");
        Client client = clientRegistration.registerWithReturn("client", "Sophia-Antipolis", advisor);
        //accountRegistry.register(500, client);

    }
}
