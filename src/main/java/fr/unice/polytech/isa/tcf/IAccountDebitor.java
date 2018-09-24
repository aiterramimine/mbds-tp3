package fr.unice.polytech.isa.tcf;

import javax.ejb.Local;

@Local
public interface IAccountDebitor {

    void debit(String accountId, double amount);

}
