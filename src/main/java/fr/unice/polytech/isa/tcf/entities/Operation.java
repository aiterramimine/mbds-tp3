package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Operation implements Serializable {

    @Id
    @NotNull
    private Integer id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Account account;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    private double amount;

    private LocalDate date;

    public Operation() {

    }

    public Operation(Account account, OperationType operationType, double amount) {
        this.account = account;
        this.operationType = operationType;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }
}

