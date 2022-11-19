/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import java.util.Base64;

/**
 *
 * @author Admin
 */
public class PasswordEncodeDecode {
    //this mehode encode the password
    public static String getEncodedString(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
    //this method decode the password
    public static String getDecodeString(String encryptedPassword){
        return new String(Base64.getMimeDecoder().decode(encryptedPassword));
    }
  
}
