package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Advisor extends Person implements Serializable {
    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    private Collection<Client> clients;

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public Advisor() {
        super();
        clients = new ArrayList<>();
    }

    public Advisor(String name, String address) {
        super(name, address);
        clients = new ArrayList<>();
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public boolean isAdvisor() {
        return true;
    }

    @Override
    public boolean isAdministrator() {
        return false;
    }

    public void addClient(Client c) {
        clients.add(c);
    }
}
