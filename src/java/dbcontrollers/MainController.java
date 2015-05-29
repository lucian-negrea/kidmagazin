/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbcontrollers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbeans.Produs;
import mbeans.User;

/**
 *
 * @author RO100051
 */
public class MainController {
    public final static String SALT = "1qtKzbpvdbRHkdqGGLEfnCrb2fWkvM5FeGwhrsFKRTY4yFQqvyEBnfca";
    private static MainController singleton;
    private static Connection con;
    
    private static Properties props = new Properties();
    private static String db = "jdbc:mysql://kidmagazin.ro/kidmagaz_presta";
       
    
    
    private MainController(){
        
        try {
            props.put("user", "kidmagaz_stoc");         
            props.put("password", "qwaszx.,123");
            props.put("autoReconnect","true");
            props.put("useUnicode", "true");
            props.put("useServerPrepStmts", "false"); // use client-side prepared statement
            props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here
            Class.forName("com.mysql.jdbc.Driver");
                       
            con = DriverManager.getConnection(db,props);
            System.out.println("Connected to: " + db);            
        } catch (Exception ex) {
            System.out.println("Connection failed: " + db + "\n" + ex.getMessage() );
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static MainController getInstance(){
       if(singleton == null)singleton=new MainController();
        return singleton;
    }
    
    public synchronized HashMap<String,String> getMapProdusCantitate(){
        HashMap<String,String> stocSite = new HashMap<>();
        try {
            if(!con.isValid(1000)){
                con = DriverManager.getConnection(db,props);
            }
            
            PreparedStatement pr = con.prepareStatement("select distinct p.reference, s.quantity from ps_product p inner join ps_stock_available s on(p.id_product=s.id_product)");
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                String referinta = rs.getString("reference");
                String cantitate = rs.getString("quantity");
                stocSite.put(referinta, cantitate);
                //System.out.println("Added " + referinta + "/" + cantitate);
            }
            System.out.println("Map has: " + stocSite.size());
            return stocSite;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            return stocSite;
        }
    }
    
    public synchronized HashMap<String,String> getMapProdusPret(){
        HashMap<String,String> preturiMagazin = new HashMap<>();
        try {
            if(!con.isValid(1000)){
                con = DriverManager.getConnection(db,props);
            }
            
            PreparedStatement pr = con.prepareStatement("select distinct p.reference, s.price from ps_product p inner join ps_product_shop s on(p.id_product=s.id_product)");
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                String referinta = rs.getString("reference");
                String pretMagazin = rs.getString("price");
                preturiMagazin.put(referinta, pretMagazin);
                //System.out.println("Added " + referinta + "/" + cantitate);
            }
            System.out.println("Map has: " + preturiMagazin.size());
            return preturiMagazin;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            return preturiMagazin;
        }
    }
    
    public boolean actualizeazaStoc(Produs p){
        try {
            if(!con.isValid(1000)){
                con = DriverManager.getConnection(db,props);
            }
            PreparedStatement pr = con.prepareStatement("update ps_product p inner join ps_stock_available s on(p.id_product=s.id_product) set s.quantity=? where p.reference=?");
            pr.setInt(1, p.getCantitateSite());
            pr.setString(2, p.getCodProdus());
            System.out.println("Stock updated " + p.getCodProdus() + ":" + p.getCantitateSite());
            int resultat = pr.executeUpdate();
            System.out.println( resultat + " produse actualizat(e)");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean actualizeazaPret(Produs p){
        try {
            if(!con.isValid(1000)){
                con = DriverManager.getConnection(db,props);
            }
            PreparedStatement pr = con.prepareStatement("update ps_product p inner join ps_product_shop s on(p.id_product=s.id_product) set s.price=? where p.reference=?");
            pr.setDouble(1, p.getPretMagazin());
            pr.setString(2, p.getCodProdus());
            System.out.println("Price updated " + p.getCodProdus() + ":" + p.getPretMagazin());
            int resultat = pr.executeUpdate();
            System.out.println( resultat + " produse actualizat(e)");
            return true;
        }catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }
    
    public User getUserByUsername(String email){
        User u = new User();
        try {
            if(!con.isValid(1000)){
                con = DriverManager.getConnection(db,props);
            }
            PreparedStatement pr = con.prepareStatement("select * from ps_employee where email=?");
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                String nume = rs.getString("lastname");
                String parola = rs.getString("passwd");
                u.setEmail(email);
                u.setParola(parola);
                u.setNume(nume);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
