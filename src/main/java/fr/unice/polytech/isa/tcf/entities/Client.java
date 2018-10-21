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
        ownAccounts = new ArrayList<>();
        comanagedAccounts = new ArrayList<>();
    }

//    public Client(String name, String address) {
//        super(name, address);
//        ownAccounts = new ArrayList<>();
//        comanagedAccounts = new ArrayList<>();
//    }

    public Client(String name, Address address) {
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

    public void addComanagedAccount(Account a) {
        comanagedAccounts.add(a);
    }

    public void addOwnAccount(Account a) {
        this.ownAccounts.add(a);
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
        if (!(o instanceof Client))
            return false;

        Client customer = (Client) o;

        if (getName() != null ? !getName().equals(customer.getName()) : customer.getName() != null)
            return false;

        return true;

    }

    @Override
    public String toString() {
        return "client {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + getAddress() + '\'' +
                ", address='" + getAddress() + '\'' +
                '}';
    }

    @Override
    public boolean isAClient() {
        return true;
    }

    @Override
    public boolean isAnAdvisor() {
        return false;
    }

    @Override
    public boolean isAnAdministrator() {
        return false;
    }
}
