package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.entities.*;

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
    private AdvisorFinder advisorFinder;

    @EJB
    private ClientRegistration clientRegistration;

    @EJB private IAccountRegistry accountRegistry;


    @PostConstruct
    public void init() {
        Address ad1 = new Address("Nice", "rue des lilas", 06000);
        Address ad2 = new Address("Paris", "rue leclerc", 92000);
        Address ad3 =new Address("Vesoul", "place de la republique", 70000);

        administratorRegistration.register("admin", "Nice", "rue des lilas", 06000);
        Advisor advisor = advisorRegistration.registerWithReturn("advisor", "Paris", "rue leclerc", 92000);


        accountRegistry.register(75, "client", "Vesoul", "place de la republique", 70000);

        //accountRegistry.register(500, client);

    }
}
