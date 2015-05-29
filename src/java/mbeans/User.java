/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeans;

import dbcontrollers.MainController;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RO100051
 */

public class User implements Serializable{
    private boolean isAuthenticated;
    private String nume,email,parola;

    public User() {
    }

    public User(String nume, String email, String parola) {
        this.nume = nume;
        this.email = email;
        this.parola = parola;
    }

    public User(boolean isAuthenticated, String nume, String email, String parola) {
        this.isAuthenticated = isAuthenticated;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
    
    public String login(){
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        User u = MainController.getInstance().getUserByUsername(email);
        String cryptPass = DigestUtils.md5Hex(MainController.SALT+parola);
        isAuthenticated = false;
        if(email != null && email.equals(u.getEmail()) && parola != null && cryptPass.equals(u.getParola())){
            isAuthenticated = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome",u.getNume());
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("isAuthenticated", isAuthenticated);
            return "SUCCESS";
        }else{
            isAuthenticated = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Loggin Error","Invalid credentials");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("isAuthenticated", isAuthenticated);
        return "FAIL";
    }
    
}
