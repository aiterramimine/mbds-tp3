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
            fetch = FetchType.EAGER,
            mappedBy = "advisor")
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

    public Advisor(String name, Address address) {
        super(name, address);
        clients = new ArrayList<>();
    }

    @Override
    public boolean isAClient() {
        return false;
    }

    @Override
    public boolean isAnAdvisor() {
        return true;
    }

    @Override
    public boolean isAnAdministrator() {
        return false;
    }

    public void addClient(Client c) {
        clients.add(c);
    }

    public int getNumClients() {
        return clients.size();
    }
}
