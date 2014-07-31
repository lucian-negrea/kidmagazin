/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProduseXML;

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

/**
 *
 * @author RO100051
 */
public class ProduseXML {
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private Element root;
    
    public ProduseXML(){
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse("http://www.smart-baby.ro/xmlproduse.xml");
        } catch (Exception ex) {
            Logger.getLogger(ProduseXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<Produs>();
        NodeList nl = doc.getElementsByTagName("produs");
        for(int i=0;i<nl.getLength();i++){
            Produs p = new Produs();
            Element produs = (Element) nl.item(i);
            
            NodeList nlNume = produs.getElementsByTagName("titlu");
            Element elNume = (Element) nlNume.item(0);
            p.setNume(elNume.getTextContent());
            
            NodeList nlCod = produs.getElementsByTagName("cod");
            Element elCod = (Element) nlCod.item(0);
            p.setCodProdus(elCod.getTextContent());
            
            NodeList nlCantitate = produs.getElementsByTagName("stoc");
            Element elCantitate = (Element) nlCantitate.item(0);
            try{
                int cantitate = Integer.parseInt(elCantitate.getTextContent());
                p.setCantitate(Integer.parseInt(elCantitate.getTextContent()));
                p.setInStoc(true);
            }catch(Exception exp){
                p.setCantitate(0);
                p.setInStoc(false);
            }
            produse.add(p);
        }
        return produse;
    }
}
