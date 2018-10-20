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


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Client owner;

    private double balance;

    @ManyToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinTable(name = "client_account")
    private Collection<Client> comanagers;

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            mappedBy = "account")
    private Collection<Operation> operations;


    public Account() {
        comanagers = new ArrayList<>();
        operations = new ArrayList<>();
    }

    public Account(Client owner, Integer balance) {
        this.owner = owner;
        this.balance = balance;
        comanagers = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer compteBanquaireId) {
        this.id = compteBanquaireId;
    }

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

    public Collection<Client> getComanagers() {
        return comanagers;
    }

    public void setComanagers(Collection<Client> comanagers) {
        this.comanagers = comanagers;
    }

    public void addComanagers(Client comanager) {
        comanagers.add(comanager);
    }

    public void addOperation(Operation operation){
        operations.add(operation);
    }
}

