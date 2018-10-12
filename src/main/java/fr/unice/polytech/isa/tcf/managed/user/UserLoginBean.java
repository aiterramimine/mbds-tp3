package fr.unice.polytech.isa.tcf.managed.user;

import org.apache.openejb.monitoring.Managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userLoginBean")
@SessionScoped
public class UserLoginBean {

    public Integer id;
    public String username;
    public String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        return "../accounts/index?faces-redirect=true&includeViewParams=true";
    }
}
