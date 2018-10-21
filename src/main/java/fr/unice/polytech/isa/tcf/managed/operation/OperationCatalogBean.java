package fr.unice.polytech.isa.tcf.managed.operation;


import fr.unice.polytech.isa.tcf.IClientFinder;
import fr.unice.polytech.isa.tcf.components.OperationFinderBean;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.entities.Operation;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class OperationCatalogBean {
    private List<Operation> operations;

    @EJB
    private OperationFinderBean operationFinder;

    @PostConstruct
    public void init() {
        operations = new ArrayList<>();
        operations = operationFinder.findAll();
        System.out.println(operations);
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operationFinder.findAll();
    }


}