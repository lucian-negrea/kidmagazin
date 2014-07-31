/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeans;

import controllers.ProduseController;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author RO100051
 */
public class Produs implements Serializable{
    private String nume, codProdus;
    private int cantitate;
    boolean inStoc;

    public Produs() {
    }

    public Produs(String nume, String codProdus, int cantitate, boolean inStoc) {
        this.nume = nume;
        this.codProdus = codProdus;
        this.cantitate = cantitate;
        this.inStoc = inStoc;
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

    public boolean isInStoc() {
        return inStoc;
    }

    public void setInStoc(boolean inStoc) {
        this.inStoc = inStoc;
    }
    
    public ArrayList<Produs> getProduse(){
        return ProduseController.getInstance().getProduse();
    }
    
}
