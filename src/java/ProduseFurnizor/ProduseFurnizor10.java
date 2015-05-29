/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProduseFurnizor;

import controllers.ProduseController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mbeans.Produs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author RO100051
 */
public class ProduseFurnizor10 {
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private Element root;
    String url ="http://www.bebebrands.ro/produse.xml";
    
    public ProduseFurnizor10(){
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList();
        
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(url);
            NodeList nl = doc.getElementsByTagName("product");
            System.out.println("Connected to " + url);
            for(int i = 0; i < nl.getLength(); i++){
                Produs p = new Produs(null, null, null, 0, 0,false);
                p.setFurnizor("Bebe barands");
                Element produs = (Element) nl.item(i);
                
                NodeList nlNume = produs.getElementsByTagName("title");
                Element elNume = (Element) nlNume.item(0);
                p.setNume(elNume.getTextContent());
                
                NodeList nlCod = produs.getElementsByTagName("id");
                Element elCod = (Element) nlCod.item(0);
                p.setCodProdus(elCod.getTextContent());
                
                NodeList nlCantitate = produs.getElementsByTagName("availability");
                Element elCantitate = (Element) nlCantitate.item(0);
                if(elCantitate.getTextContent().trim().equalsIgnoreCase("yes")){
                    p.setCantitate(10);
                }else{
                    p.setCantitate(0);
                }
                
                int cantitateSite = ProduseController.getInstance().getStocSite(p);
                if(cantitateSite!=99999){
                    p.setCantitateSite(cantitateSite);
                    p.setInSite(true);
                }else{
                    p.setCantitateSite(0);
                }
                
                NodeList nlPrice = produs.getElementsByTagName("price");
                Element elPrice = (Element) nlPrice.item(0);
                double pretFurnizor = Double.parseDouble(elPrice.getTextContent());
                
                NodeList nlSpecialPrice = produs.getElementsByTagName("specialprice");
                Element elSpecialPrice = (Element) nlSpecialPrice.item(0);
                double pretSpecialFurnizor = 0;
                try{
                    pretSpecialFurnizor = Double.parseDouble(elSpecialPrice.getTextContent());
                }catch(Exception exp){
                    //exp.printStackTrace();
                }
                
                if((pretSpecialFurnizor != 0) && (pretSpecialFurnizor < pretFurnizor)){
                    p.setPretFurnizor(pretSpecialFurnizor);
                }else{
                    p.setPretFurnizor(pretFurnizor);
                }
                produse.add(p);
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProduseFurnizor10.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ProduseFurnizor10.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProduseFurnizor10.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produse;
    }
}
