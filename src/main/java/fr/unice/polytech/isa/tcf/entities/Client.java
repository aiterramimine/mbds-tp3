package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Client extends Person implements Serializable {

    @ManyToOne
    private Advisor advisor;

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            mappedBy = "owner")
    private Collection<Account> ownAccounts;

    @ManyToMany(mappedBy = "comanagers")
    private Collection<Account> comanagedAccounts;


    public Client() {
    }

    public Client(String name, String address) {
        super(name, address);
        ownAccounts = new ArrayList<>();
        comanagedAccounts = new ArrayList<>();
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }


    public Collection<Account> getOwnAccounts() {
        return ownAccounts;
    }

    public void setOwnAccounts(Collection<Account> ownAccounts) {
        this.ownAccounts = ownAccounts;
    }

    public Collection<Account> getComanagedAccounts() {
        return comanagedAccounts;
    }

    public void setComanagedAccounts(Collection<Account> comanagedAccount) {
        this.comanagedAccounts = comanagedAccount;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;

        Customer customer = (Customer) o;

        if (getName() != null ? !getName().equals(customer.getName()) : customer.getName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(customer.getCreditCard()) : customer.getCreditCard() != null)
            return false;
        return true;

    }

    @Override
    public String toString() {
        return "Client {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + getAddress() + '\'' +
                ", address='" + getAddress() + '\'' +
                '}';
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public boolean isAdvisor() {
        return false;
    }

    @Override
    public boolean isAdministrator() {
        return false;
    }
}
