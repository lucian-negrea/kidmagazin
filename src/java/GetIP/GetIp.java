/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GetIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author RO100051
 */
public class GetIp {
    public static String getIp(){
        BufferedReader in = null;
        try{
            URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
            in = new BufferedReader(new InputStreamReader(whatIsMyIp.openStream()));
            String ip = in.readLine();
            return ip;
        }
        catch(Exception exp){
            exp.printStackTrace();
        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(Exception exp){
                    exp.printStackTrace();
                }
            }
        }
        return null;
    }
}
    

