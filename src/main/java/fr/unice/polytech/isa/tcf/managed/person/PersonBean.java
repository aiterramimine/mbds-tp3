package fr.unice.polytech.isa.tcf.managed.person;

import fr.unice.polytech.isa.tcf.*;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.managed.AccountBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(name="personBean")
@RequestScoped
public class PersonBean implements Serializable {

    @EJB private AdministratorRegistration administratorRegistration;

    @EJB private AdvisorRegistration advisorRegistration;

    @EJB private ClientRegistration clientRegistration;

    @EJB private IClientFinder clientFinder;

    @EJB private AdvisorFinder advisorFinder;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private String name;

    private String address;

    private String role;

    private String selectedAdvisorId;

//    public String select() {
//        if(finder.findById(getId()).isPresent()) {
//            Account a = finder.findById(getId()).get();
//            balance = a.getBalance();
//            return "manage?faces-redirect=true&includeViewParams=true";
//        } else {
//            FacesContext.getCurrentInstance()
//                    .addMessage("form-error", new FacesMessage("Aucun compte avec l'identifiant : " + getId()));
//            return Signal.UNKOWN;
//        }
//    }

    public String create() {

        try {

            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String role = req.getParameter("role");

            int idCreated;

            System.out.println("Trying to create " + this.role + " with name " + name + " and ADDRESS : " + address);


            if (this.role.equals("client")) {
                //int idAdvisor = Integer.parseInt(req.getParameter("idAdvisor"));
                Advisor advisor = advisorFinder.findById(Integer.parseInt(selectedAdvisorId));

                idCreated = clientRegistration.register(name, address, advisor);
                System.out.print("New client : " + idCreated + " | advisor : " + advisor.getName() + " | ");

            } else if (this.role.equals("advisor")) {
                idCreated = advisorRegistration.register(getName(), getAddress());
                System.out.print("New advisor : " + idCreated + " | ");
            } else {
                idCreated = administratorRegistration.register(getName(), getAddress());
                System.out.print("New administrator : " + idCreated + " | ");
            }

            System.out.println("name : " + getName() + " | address : " + getAddress());
            return "../accounts/index?faces-redirect=true&includeViewParams=true";
        } catch (Exception e) {
            System.out.print("error creating person : ");
            e.printStackTrace();
            return "../accounts/index?faces-redirect=true&includeViewParams=true";
        }
    }


    public String search() {
        System.out.println("Clicked on the button");
        return "catalog?faces-redirect=true&includeViewParams=true";
    }

    public void roleChanged(ValueChangeEvent event) {
        System.out.println(event.getNewValue().toString());
        this.role = event.getNewValue().toString();
    }

    public List<Advisor> getAllAdvisors() {
        return advisorFinder.findAll();
    }

    public List<Client> getAllClients() {
        return clientFinder.findAll();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setSelectedAdvisorId(String advisorId) {
        selectedAdvisorId = advisorId;
    }

    public String getSelectedAdvisorId() {
        return selectedAdvisorId;
    }

}
