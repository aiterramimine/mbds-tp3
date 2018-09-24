package fr.unice.polytech.isa.tcf;

import javax.ejb.Local;

@Local
public interface IAccountCreditor {

    void credit(int accountId, double amount);

}
