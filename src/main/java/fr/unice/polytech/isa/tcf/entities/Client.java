package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "\\d{10}+", message = "Numéro de carte de crédit invalide")
    private String creditCardNum;

    @NotNull
    @DecimalMin(value="50.00", message = "Vous devez transférer au minimum 50 euros à la création du compte")
    private double minTransfert;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCard() {
        return creditCardNum;
    }

    public void setCreditCard(String creditCard) {
        this.creditCardNum = creditCard;
    }

    public double getMinTransfert() {
        return minTransfert;
    }

    public void setMinTransfert(double minTransfert) {
        this.minTransfert = minTransfert;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreditCard() != null ? getCreditCard().hashCode() : 0);
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
        if (getCreditCard() != null ? !getCreditCard().equals(customer.getCreditCard()) : customer.getCreditCard() != null)
            return false;
        return true;

    }

    @Override
    public String toString() {
        return "Client {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditCardNumber='" + creditCardNum + '\'' +
                ", minimalTransfert='" + minTransfert + '\'' +
                '}';
    }
}
