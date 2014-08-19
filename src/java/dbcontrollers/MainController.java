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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import mbeans.Produs;

/**
 *
 * @author RO100051
 */
public class MainController {
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
}
