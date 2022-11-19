/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
public class Helper {
    
    static public String shortDisProvider(String dis){
        String shortDisArr[]=dis.split(" ");
        String shortDis="";
        if(shortDisArr.length>10){
            for(int i=0;i<10;i++){
                shortDis +=" "+shortDisArr[i];
            }
            return shortDis+" ...";
        }else{
            return dis+"...";
        }
        
    }
    
    public static void saveTheImageToFolder(String path,Part part){
        try{
            //save the image to folder...
            FileOutputStream fos=new FileOutputStream(path);
            InputStream is=part.getInputStream();
            byte data[]=new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
            
        }catch(Exception e){
            System.out.println("!!!!! opps image not saved !!!!!");
            e.printStackTrace();
        }
    }
    
    public static int getRandomeNumber(int range){
        Random rand =new Random();
        return rand.nextInt(range);
    }
    
}
