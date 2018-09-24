package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.CompteBanquaireOperation;
import fr.unice.polytech.isa.tcf.entities.CompteBanquaire;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CompteBanquaireBean implements CompteBanquaireOperation {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CompteBanquaire getCompteBanquaireById(int id) {
        return entityManager.find(CompteBanquaire.class, id);
    }

    public void deposer(int id, int montant) {
        CompteBanquaire compteBanquaire = getCompteBanquaireById(id);
        compteBanquaire.setSolde(compteBanquaire.getSolde() + montant);
    }

    @Override
    public int retirer(int id, int montant) {
        CompteBanquaire compteBanquaire = getCompteBanquaireById(id);
        if (montant < compteBanquaire.getSolde()) {
            compteBanquaire.setSolde(compteBanquaire.getSolde() - montant);
            return montant;
        } else {
            return 0;
        }
    }
}
