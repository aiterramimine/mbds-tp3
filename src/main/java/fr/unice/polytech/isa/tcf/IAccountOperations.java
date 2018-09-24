package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;

import java.util.List;

public interface IAccountOperations {

    void deposit(int id, int amount);

    int withdraw(int id, int amount);

    void createAccount(Account c);

    void createAccountsTest();
}
