/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ProduseFurnizor.ProduseFurnizor1;
import ProduseFurnizor.ProduseFurnizor10;
import ProduseFurnizor.ProduseFurnizor2;
import ProduseFurnizor.ProduseFurnizor3;
import ProduseFurnizor.ProduseFurnizor4;
import ProduseFurnizor.ProduseFurnizor5;
import ProduseFurnizor.ProduseFurnizor6;
import ProduseFurnizor.ProduseFurnizor7;
import ProduseFurnizor.ProduseFurnizor8;
import ProduseFurnizor.ProduseFurnizor9;
import dbcontrollers.MainController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbeans.Produs;

/**
 *
 * @author RO100051
 */
public class ProduseController {

    private final int NUESTEINSITE = 99999;
    private static ProduseController singleton;
    ArrayList<Produs> produseFurnizor = new ArrayList<>();
    private HashMap<String, String> stocSite;
    private HashMap<String, String> preturiMagazin;
    private final ProduseFurnizor1 produseFurnizor1 = new ProduseFurnizor1();
    private final ProduseFurnizor2 produseFurnizor2 = new ProduseFurnizor2();
    private final ProduseFurnizor3 produseFurnizor3 = new ProduseFurnizor3();
    private final ProduseFurnizor4 produseFurnizor4 = new ProduseFurnizor4();
    private final ProduseFurnizor5 produseFurnizor5 = new ProduseFurnizor5();
    private final ProduseFurnizor6 produseFurnizor6 = new ProduseFurnizor6();
    private final ProduseFurnizor7 produseFurnizor7 = new ProduseFurnizor7();
    private final ProduseFurnizor8 produseFurnizor8 = new ProduseFurnizor8();
    private final ProduseFurnizor9 produseFurnizor9 = new ProduseFurnizor9();
    private final ProduseFurnizor10 produseFurnizor10 = new ProduseFurnizor10();

    private ProduseController() {
    }

    public static ProduseController getInstance() {
        if (singleton == null) {
            singleton = new ProduseController();
        }
        return singleton;
    }

    public int getStocSite(Produs p) {
        for (Map.Entry<String, String> key : stocSite.entrySet()) {
            String cod = key.getKey();
            String cantitate = key.getValue();
            if (cod.trim().equalsIgnoreCase(p.getCodProdus().trim())) {
                return Integer.parseInt(cantitate);
            }
        }
        return NUESTEINSITE;
    }
    
    public double getPretMagazin(Produs p) {
        for (Map.Entry<String, String> key : preturiMagazin.entrySet()) {
            String cod = key.getKey();
            String pretMagazin = key.getValue();
            if (cod.trim().equalsIgnoreCase(p.getCodProdus().trim())) {
                return Double.parseDouble(pretMagazin);
            }
        }
        return 0;
    }
    

    public ArrayList<Produs> getProduse() {
        stocSite = MainController.getInstance().getMapProdusCantitate();
        preturiMagazin = MainController.getInstance().getMapProdusPret();

        produseFurnizor.clear();
        System.out.println("Lista produselor a fost stearsa!");

        Thread t1 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor1 = produseFurnizor1.getProduse();
                produseFurnizor.addAll(listProduseFurnizor1);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor1: Added " + listProduseFurnizor1.size() + " produse in " + duration/1000000);
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor2 = produseFurnizor2.getProduse();
                produseFurnizor.addAll(listProduseFurnizor2);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor2: Added " + listProduseFurnizor2.size() + " produse in " + duration/1000000);
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor3 = produseFurnizor3.getProduse();
                produseFurnizor.addAll(listProduseFurnizor3);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor3: Added " + listProduseFurnizor3.size() + " produse in " + duration/1000000);
            }
        };

        Thread t4 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor4 = produseFurnizor4.getProduse();
                produseFurnizor.addAll(listProduseFurnizor4);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor4: Added " + listProduseFurnizor4.size() + " produse in " + duration/1000000);
            }
        };

        Thread t5 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor5 = produseFurnizor5.getProduse();
                produseFurnizor.addAll(listProduseFurnizor5);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor5: Added " + listProduseFurnizor5.size() + " produse in " + duration/1000000);
            }
        };

        Thread t6 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor6 = produseFurnizor6.getProduse();
                produseFurnizor.addAll(listProduseFurnizor6);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor6: Added " + listProduseFurnizor6.size() + " produse in " + duration/1000000);
            }
        };

        Thread t7 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor7 = produseFurnizor7.getProduse();
                produseFurnizor.addAll(listProduseFurnizor7);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor7: Added " + listProduseFurnizor7.size() + " produse in " + duration/1000000);
            }
        };

        Thread t8 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor8 = produseFurnizor8.getProduse();
                produseFurnizor.addAll(listProduseFurnizor8);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor8: Added " + listProduseFurnizor8.size() + " produse in " + duration/1000000);
            }
        };

        Thread t9 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor9 = produseFurnizor9.getProduse();
                produseFurnizor.addAll(listProduseFurnizor9);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor9: Added " + listProduseFurnizor9.size() + " produse in " + duration/1000000);
            }
        };
        
        Thread t10 = new Thread() {
            public void run() {
                long startTime = System.nanoTime();
                ArrayList<Produs> listProduseFurnizor10 = produseFurnizor10.getProduse();
                produseFurnizor.addAll(listProduseFurnizor10);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Furnizor10: Added " + listProduseFurnizor10.size() + " produse in " + duration/1000000);
            }
        };
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();
            
        try {
            t1.join();t2.join();t3.join();t4.join();t5.join();t6.join();t7.join();t8.join();t9.join();t10.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProduseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Produs p: produseFurnizor){
            double pretMagazin = getPretMagazin(p);
            p.setPretMagazin(pretMagazin);
        }
        
        ArrayList<String> codProduseFurnizori = new ArrayList<>();
        for(Produs p: produseFurnizor){
            codProduseFurnizori.add(p.getCodProdus().trim());
        }
        
        ArrayList<String> codProduseSite = new ArrayList<>();
        for(Map.Entry<String, String> key : stocSite.entrySet()){
            codProduseSite.add(key.getKey());
        }
        System.out.println("Site:" + codProduseSite.size() + " produse.");
        System.out.println("Furnizori:" + codProduseFurnizori.size() + " produse.");
        
        for (Map.Entry<String, String> key : stocSite.entrySet()) {
            
            String cod = key.getKey();
            String cantitate = key.getValue();
            //System.out.println("Checking: " + cod);
                
                if (!(codProduseFurnizori.contains(cod.trim()))) {
                    //System.out.println("Produsul " + cod + "/" + cantitate + " nu se regaseste la furnizori!");
                    Produs produs = new Produs();
                    produs.setCantitateSite(Integer.parseInt(cantitate));
                    produs.setCodProdus(cod);
                    produs.setFurnizor("Nedefinit");
                    produs.setNume(cod);
                    produs.setCantitate(0);
                    produs.setPretFurnizor(0);
                    produs.setPretMagazin(getPretMagazin(produs));
                    produseFurnizor.add(produs);
                }
        }
        
        return produseFurnizor;
    }

}
