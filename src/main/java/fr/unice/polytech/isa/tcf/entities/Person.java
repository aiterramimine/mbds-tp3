package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @NotNull
    @Column(unique = true)
    protected String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @MapsId
    protected Address address;

    public Person() {
    }

//    public Person(String name, String address) {
//        this.name = name;
//        this.address = new Address();
//    }

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract boolean isAClient();

    public abstract boolean isAnAdvisor();

    public abstract boolean isAnAdministrator();

}
