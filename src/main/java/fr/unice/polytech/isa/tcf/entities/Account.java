package fr.unice.polytech.isa.tcf.entities;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;

@Entity
@Table(name= "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String clientName;

    @DecimalMin(value = Constants.ACCOUNT_CREATION_MIN_TRANSFER, message="Le montant minimal du dépot initial est 50.00 euros")
    private double balance;

    public Account() {

    }

    public Account(String clientName, Integer balance) {
        this.clientName = clientName;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer compteBnquaireId) {
        this.id = compteBnquaireId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
