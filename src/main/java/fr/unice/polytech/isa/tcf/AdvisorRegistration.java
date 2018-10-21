package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Advisor;

public interface AdvisorRegistration {
    public int register(String name, String town, String address, int zipcode);

    Advisor registerWithReturn(String name, String town, String address, int zipcode);
}
