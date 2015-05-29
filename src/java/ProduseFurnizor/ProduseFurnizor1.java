/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProduseFurnizor;

import controllers.ProduseController;
import java.util.ArrayList;
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
public class ProduseFurnizor1 {

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private final String url = "http://www.onlinelogistics.ro/media/productsfeed/xml-feed-general.xml";

    public ProduseFurnizor1() {
        
    }

    public ArrayList<Produs> getProduse() {
        ArrayList<Produs> produse = new ArrayList<>();
        
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(url);
            NodeList nl = doc.getElementsByTagName("product");
            System.out.println("Connected to " + url);
            for (int i = 0; i < nl.getLength(); i++) {
                Produs p = new Produs(null, null, null, 0, 0,false);
                p.setFurnizor("Smart baby");
                Element produs = (Element) nl.item(i);

                NodeList nlNume = produs.getElementsByTagName("nume");
                Element elNume = (Element) nlNume.item(0);
                p.setNume(elNume.getTextContent());

                NodeList nlCod = produs.getElementsByTagName("cod_produs");
                Element elCod = (Element) nlCod.item(0);
                p.setCodProdus(elCod.getTextContent());
                
                NodeList nlPret = produs.getElementsByTagName("Pret_recomandat_de_vanzare_TVA_inclus");
                Element elPret = (Element) nlPret.item(0);
                String pretFurnizor = elPret.getTextContent().replace("RON","").trim();
                p.setPretFurnizor(Double.parseDouble(pretFurnizor));
                                
                NodeList nlCantitate = produs.getElementsByTagName("stoc_produs");
                Element elCantitate = (Element) nlCantitate.item(0);
                if(elCantitate.getTextContent().contains("In stoc")){
                    p.setCantitate(10);
                }else{
                    p.setCantitate(0);
                }
//               
                int cantitateSite = ProduseController.getInstance().getStocSite(p);
                if(cantitateSite!=99999){
                    p.setCantitateSite(cantitateSite);
                    p.setInSite(true);
                }else{
                    p.setCantitateSite(0);
                }
                           
                produse.add(p);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return produse;
    }
}
