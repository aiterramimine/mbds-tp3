package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;

import java.util.List;

public interface IAccountRegistry {
    public Account getAccountById(int id);

    List<Account> getAllAccounts();
}
