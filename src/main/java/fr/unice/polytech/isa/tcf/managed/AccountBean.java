package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.components.AccountOperationsBean;
import fr.unice.polytech.isa.tcf.components.AccountRegistryBean;
import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class AccountBean implements Serializable {

    @EJB
    private AccountRegistryBean registry;

    @EJB
    private AccountOperationsBean operations;

    String clientName;
    int balance;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void createAccount() {
        try {
            operations.createAccount(new Account(this.getClientName(), this.getBalance()));
        } catch (Exception e) {
            System.out.print("error creating account : ");
            e.printStackTrace();
        }

    }
}
