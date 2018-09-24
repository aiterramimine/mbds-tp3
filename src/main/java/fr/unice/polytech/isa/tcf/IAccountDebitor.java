package fr.unice.polytech.isa.tcf;

import javax.ejb.Local;

@Local
public interface IAccountDebitor {

    double debit(int accountId, double amount);

}
