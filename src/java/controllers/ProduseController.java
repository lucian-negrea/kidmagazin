/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import ProduseXML.ProduseXML;
import java.util.ArrayList;
import mbeans.Produs;

/**
 *
 * @author RO100051
 */
public class ProduseController {
    private static ProduseController singleton;
    private ProduseXML produseXML;
    private ProduseController(){
        produseXML = new ProduseXML();
    }
    
    public static ProduseController getInstance(){
        if(singleton==null)singleton=new ProduseController();
        return singleton;
    }
    
    public ArrayList<Produs> getProduse(){
        return produseXML.getProduse();
        
    }
    
}
