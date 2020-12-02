/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author edubi
 */
public class Postals {
   
   public String pathOrigin;
   public String pathPostal;
   public String namePostal;
   public String namePostalExtension;

   public Postals sig;

    public Postals(String pathOrigin, String pathPostal , String namePostal, String namePostalExtension){
        this.pathOrigin = pathOrigin;
        this.pathPostal = pathPostal;
        this.namePostal = namePostal;
        this.namePostalExtension = namePostalExtension;
    }
 
}
 