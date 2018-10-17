package fr.unice.polytech.isa.tcf.managed;

import fr.unice.polytech.isa.tcf.AdministratorRegistration;
import fr.unice.polytech.isa.tcf.AdvisorRegistration;
import fr.unice.polytech.isa.tcf.ClientRegistration;
import fr.unice.polytech.isa.tcf.entities.Advisor;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean(name="personBean")
@RequestScoped
public class PersonBean implements Serializable {

    @EJB private AdministratorRegistration administratorRegistration;

    @EJB private AdvisorRegistration advisorRegistration;

    @EJB private ClientRegistration clientRegistration;

    private static final Logger log = Logger.getLogger(AccountBean.class.getName());

    private Integer id;

    private String name;

    private String address;

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

            System.out.println("Trying to create " + role + " with name " + name + " and ADDRESS : " + address);

            if (role == "Client") {
                int idAdvisor = Integer.parseInt(req.getParameter("idAdvisor"));
                //ToDo : client registration

            } else if (role == "Administrator") {
                idCreated = administratorRegistration.register(getName(), getAddress());
                System.out.print("New administrator : " + idCreated + " | ");
            } else {
                idCreated = advisorRegistration.register(getName(), getAddress());
                System.out.print("New advisor : " + idCreated + " | ");
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

}
