/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProduseFurnizor;

import controllers.ProduseController;
import dbcontrollers.MainController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbeans.Produs;

/**
 *
 * @author RO100051
 */
public class ProduseFurnizor2 {

    URL urlFurnizor;
    URLConnection urlConn;
    InputStreamReader in;
    BufferedReader br;
    String url = "http://www.magazinpentrucopii.ro/carucioare-copii/datafeed.php";

    String line = "";
    String separator = "\\|";

    public ProduseFurnizor2() {
        
    }

    public ArrayList<Produs> getProduse() {
        ArrayList<Produs> produse = new ArrayList<>();
        try {
            urlFurnizor = new URL(url);
            urlConn = urlFurnizor.openConnection();
            System.out.println("Connected to " + url);
            in = new InputStreamReader(urlConn.getInputStream());
            br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                String[] produs = line.split(separator);
                Produs p = new Produs(null, null, null, 0, 0,false);
                p.setFurnizor("Magazin pentru copii");
                p.setNume(produs[4]);
                p.setCodProdus(produs[2]);
                CharSequence s = "Out";
                //System.out.println("Nume: " + produs[4] + " Stoc: " + produs[11] );
                if (produs[11].contains(s)) {
                    p.setCantitate(0);
                } else {
                    p.setCantitate(10);
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
            return produse;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ProduseFurnizor2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produse;
    }
}
