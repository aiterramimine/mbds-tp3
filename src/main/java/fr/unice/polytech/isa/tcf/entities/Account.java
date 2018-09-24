package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Account")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT c from Account c")
})
public class Account implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer accountId;

    private String clientName;

    private Integer balance;

    public Account(String clientName, Integer balance) {
        this.clientName = clientName;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer compteBnquaireId) {
        this.accountId = compteBnquaireId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
