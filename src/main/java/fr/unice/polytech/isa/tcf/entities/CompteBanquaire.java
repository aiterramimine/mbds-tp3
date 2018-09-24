package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class CompteBanquaire implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer compteBanquaireId;

    private Client client;

    private Integer solde;

    public CompteBanquaire(Client client, Integer solde) {
        this.client = client;
        this.solde = solde;
    }

    public Integer getCompteBanquaireId() {
        return compteBanquaireId;
    }

    public void setCompteBanquaireId(Integer compteBnquaireId) {
        this.compteBanquaireId = compteBnquaireId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }
}
