package fr.unice.polytech.isa.tcf.entities;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(
        name="findAllAccounts",
        query="SELECT a FROM Account a"
)
public class Account implements Serializable {

    private int id;

    private Client owner;

    private double balance;

    private Collection<Client> comanagers;


    public Account() {
        comanagers = new ArrayList<>();
    }

    public Account(Client owner, Integer balance) {
        this.owner = owner;
        this.balance = balance;
        comanagers = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer compteBanquaireId) {
        this.id = compteBanquaireId;
    }

    @ManyToOne
    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @ManyToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinTable(name = "client_account")
    public Collection<Client> getComanagers() {
        return comanagers;
    }

    public void setComanagers(Collection<Client> comanagers) {
        this.comanagers = comanagers;
    }

    public void addComanagers(Client comanager) {
        comanagers.add(comanager);
    }
}

