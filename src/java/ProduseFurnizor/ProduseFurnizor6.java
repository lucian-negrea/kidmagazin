/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProduseFurnizor;

import controllers.ProduseController;
import dbcontrollers.MainController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import mbeans.Produs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author RO100051
 */
public class ProduseFurnizor6 {
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private Element root;
    private String url = "http://www.babydreams.ro/products.xml";
    
    public ProduseFurnizor6() {
        
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<>();
        
        try{
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(url);
            NodeList nl = doc.getElementsByTagName("item");
            System.out.println("Connected to " + url);
            for(int i=1;i<nl.getLength();i++){
                Produs p = new Produs(null, null, null, 0, 0,false);
                p.setFurnizor("Baby dreams");
                Element produs = (Element) nl.item(i);

                NodeList nlNume = produs.getElementsByTagName("title");
                Element elNume = (Element) nlNume.item(0);
                p.setNume(elNume.getTextContent());
                
                NodeList nlCod = produs.getElementsByTagName("product-code");
                Element elCod = (Element) nlCod.item(0);
                p.setCodProdus(elCod.getTextContent());
                
                NodeList nlCantitate = produs.getElementsByTagName("available");
                Element elCantitate = (Element) nlCantitate.item(0);
                try {
                    int cantitate = Integer.parseInt(elCantitate.getTextContent());
                    p.setCantitate(Integer.parseInt(elCantitate.getTextContent()));
                } catch (Exception exp) {
                    p.setCantitate(0);
                }
                int cantitateSite = ProduseController.getInstance().getStocSite(p);
                if(cantitateSite!=99999){
                    p.setCantitateSite(cantitateSite);
                    p.setInSite(true);
                }else{
                    p.setCantitateSite(0);
                }
                produse.add(p);
                
            }
        }catch(Exception exp){
        }
        return produse;
    }
}
