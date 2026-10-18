/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author kevin
 */
public class ValidarEmail {
    public static boolean validar(String email){
        if(email == null || email.isBlank()){
            return false;
        }
        
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$";
        
        return email.trim().matches(regex);
        
    }
}
