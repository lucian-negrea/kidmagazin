package ProduseFurnizor;

import controllers.ProduseController;
import dbcontrollers.MainController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbeans.Produs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RO100051
 */
public class ProduseFurnizor4 {

    URL urlFurnizor;
    URLConnection urlConn;
    InputStreamReader in;
    BufferedReader br;
    
    String url = "http://evisioncompany.ro/feeds/csv_produse.aspx?mid=-1&cid=-1";
    String line = "";
    String separator = ",";
    CharSequence inStoc = "True";
    
    public ProduseFurnizor4() {
        
    }
    
    public ArrayList<Produs> getProduse() {
        ArrayList<Produs> produse = new ArrayList<>();
        try {
            
            urlFurnizor = new URL(url);
            urlConn = urlFurnizor.openConnection();
            System.out.println("Connected to " + url);
            in = new InputStreamReader(urlConn.getInputStream());
            br = new BufferedReader(in);
            while ((line=br.readLine()) != null) {
                String[] produs = line.split(separator);
                
                Produs p = new Produs(null, null, null, 0, 0,false);
                p.setFurnizor("eVision");
                p.setNume(produs[1]);
                p.setCodProdus(produs[0]);
                if(produs[6].contains(inStoc)){
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
                produse.add(p);
                //System.out.println(produse.size() + " " +p.getFurnizor() +" " + p.getNume() + " " + p.getCodProdus() + " " + p.getCantitate() +" " + p.getCantitateSite());
                
            }
                       
        } catch (Exception ex) {
            Logger.getLogger(ProduseFurnizor4.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produse;        
    }
    
}
