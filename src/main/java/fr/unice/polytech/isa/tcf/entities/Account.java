package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String clientName;

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
