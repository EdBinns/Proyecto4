 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author edubi
 */
public class Images implements Serializable {
      
   private String path;
   private String name;
   private String dateOfCreated;
   private String dimens;
   private String bytes;
   private String typeOfFile;

    public Images(String path, String name, String dateOfCreated, String dimens, String bytes, String typeOfFile) {
        this.path = path;
        this.name = name;
        this.dateOfCreated = dateOfCreated;
        this.dimens = dimens;
        this.bytes = bytes;
        this.typeOfFile = typeOfFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(String dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public String getDimens() {
        return dimens;
    }

    public void setDimens(String dimens) {
        this.dimens = dimens;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }
       
}
