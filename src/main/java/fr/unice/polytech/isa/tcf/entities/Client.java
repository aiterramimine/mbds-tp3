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

    @NotNull
    @Pattern(regexp = "\\d{10}+", message = "Numéro de carte de crédit invalide")
    private String creditCardNum;

    @NotNull
    @DecimalMin(value="50.00", message = "Vous devez transférer au minimum 50 euros à la création du compte")
    private double initialTransfer;

    private Advisor advisor;

    private Collection<Account> ownAccounts;

    private Collection<Account> comanagedAccounts;


    public Client() {
        super();
        ownAccounts = new ArrayList<>();
        comanagedAccounts = new ArrayList<>();
    }

    public Client(String name, String address) {
        super(name, address);
        ownAccounts = new ArrayList<>();
        comanagedAccounts = new ArrayList<>();
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCard) {
        this.creditCardNum = creditCard;
    }

    public double getInitialTransfer() {
        return initialTransfer;
    }

    public void setInitialTransfer(double initialTransfer) {
        this.initialTransfer = initialTransfer;
    }

    @ManyToOne
    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    @OneToMany(cascade = {CascadeType.ALL},
        fetch = FetchType.EAGER,
        mappedBy = "owner")
    public Collection<Account> getOwnAccounts() {
        return ownAccounts;
    }

    public void setOwnAccounts(Collection<Account> ownAccounts) {
        this.ownAccounts = ownAccounts;
    }

    @ManyToMany(mappedBy = "comanagers")
    public Collection<Account> getComanagedAccounts() {
        return comanagedAccounts;
    }

    public void setComanagedAccounts(Collection<Account> comanagedAccount) {
        this.comanagedAccounts = comanagedAccount;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreditCardNum() != null ? getCreditCardNum().hashCode() : 0);
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
        if (getCreditCardNum() != null ? !getCreditCardNum().equals(customer.getCreditCard()) : customer.getCreditCard() != null)
            return false;
        return true;

    }

    @Override
    public String toString() {
        return "Client {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditCardNumber='" + creditCardNum + '\'' +
                ", minimalTransfer='" + initialTransfer + '\'' +
                '}';
    }
}
