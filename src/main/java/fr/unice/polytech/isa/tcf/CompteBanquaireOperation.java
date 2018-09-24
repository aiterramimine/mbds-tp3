package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.CompteBanquaire;

public interface CompteBanquaireOperation {

    public CompteBanquaire getCompteBanquaireById(int id);

    public void deposer(int id, int montant);

    public int retirer(int id, int montant);
}
