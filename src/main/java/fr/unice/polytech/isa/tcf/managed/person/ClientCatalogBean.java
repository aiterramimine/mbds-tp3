package fr.unice.polytech.isa.tcf.managed.person;

import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ClientCatalogBean {
    private List<Client> clients;

    @EJB
    private IClientFinder clientFinder;

    @PostConstruct
    public void init() {
        clients = new ArrayList<>();
        clients = clientFinder.findAll();
        //System.out.println(clients);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clientFinder.findAll();
    }


}
