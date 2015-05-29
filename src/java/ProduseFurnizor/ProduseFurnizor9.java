/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProduseFurnizor;

import controllers.ProduseController;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbeans.Produs;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author RO100051
 */
public class ProduseFurnizor9 {
    URL urlFurnizor;
    URLConnection urlConn;
    InputStream in;
    BufferedReader br;
    String url ="http://www.hubners.ro/stoc/stoc.xls";
    
    public ProduseFurnizor9() {
        
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<>();
        try {
            
            urlFurnizor = new URL(url);
            in = urlFurnizor.openStream();
            
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row.getRowNum() != 0){
                    int stocFurnizor;
                    Produs p = new Produs(null, null, null, 0, 0,false);
                    //System.out.println("Parcurgem rand " + row.getRowNum());
                    String codProdus = row.getCell(0).getStringCellValue().trim();
                    String denumireProdus = row.getCell(1).getStringCellValue().trim();
                    switch (row.getCell(2).getCellType()){
                        case(Cell.CELL_TYPE_STRING):
                            stocFurnizor = Integer.parseInt(row.getCell(2).getStringCellValue());
                            break;
                        case(Cell.CELL_TYPE_NUMERIC):
                            stocFurnizor = (int) Math.round(row.getCell(2).getNumericCellValue());
                            break;
                        default:
                            stocFurnizor = 0;
                    }
                    
                    //System.out.println(codProdus + "/" + denumireProdus + "/" + stocFurnizor);
                    p.setFurnizor("Hubners");
                    p.setCodProdus(codProdus);
                    p.setNume(denumireProdus);
                    p.setCantitate(stocFurnizor);
                    int cantitateSite = ProduseController.getInstance().getStocSite(p);
                if(cantitateSite!=99999){
                    p.setCantitateSite(cantitateSite);
                    p.setInSite(true);
                }else{
                    p.setCantitateSite(0);
                }
                    
                    double pretFurnizor = row.getCell(3).getNumericCellValue();
                    p.setPretFurnizor(pretFurnizor);
                    produse.add(p);
                }
                
            }
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ProduseFurnizor9.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produse;
    }
}
