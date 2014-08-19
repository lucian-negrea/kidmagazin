/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeans;

import controllers.ProduseController;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author RO100051
 */

public class Produs implements Serializable{
    private String nume, codProdus,furnizor;
    private int cantitate, cantitateSite;
    private ArrayList<Produs> produse;
    
    boolean inSite;

   

    public String getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(String furnizor) {
        this.furnizor = furnizor;
    }

    public int getCantitateSite() {
        return cantitateSite;
    }

    public void setCantitateSite(int cantitateSite) {
        this.cantitateSite = cantitateSite;
    }
    
    @PostConstruct
    public void init(){
        long startTime = System.nanoTime();
        produse = ProduseController.getInstance().getProduse();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Lista de produse(" + "" + ") a fost construita in: " + duration/1000000);
    }
    
    public Produs() {
        
    }

    public Produs(String furnizor, String nume, String codProdus, int cantitate, int cantitateSite, boolean inSite) {
        this.nume = nume;
        this.codProdus = codProdus;
        this.cantitate = cantitate;
        this.cantitateSite = cantitateSite;
        
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(String codProdus) {
        this.codProdus = codProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public boolean isInSite() {
        return inSite;
    }

    public void setInSite(boolean inSite) {
        this.inSite = inSite;
    }
    
    public ArrayList<Produs> getProduse(){
        return produse;
    }
    
    public void afiseazaProduse(){
        produse = ProduseController.getInstance().getProduse();
    }
    
    public void actualizareProdus(Produs p){
        System.out.println("Furnizor: " + p.getFurnizor() + " Nume: " + p.getNume() + " Cod: " + p.getCodProdus() + " StocF: " + p.getCantitate() + " StocS: " + p.getCantitateSite());
    }
    
    public String getIp(){
        return GetIP.GetIp.getIp();
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Produs actualizat", ((Produs) event.getObject()).getNume());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        actualizareProdus((Produs) event.getObject());
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Actualizare anulata", ((Produs) event.getObject()).getNume());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
