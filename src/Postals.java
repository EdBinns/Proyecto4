/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author edubi
 */
public class Postals extends Images implements Serializable{
   
    private String nameWithExtension;

    public Postals(String path, String name, String dateOfCreated, String dimens, String bytes, String typeOfFile, String nameWithExtension) {
        super(path, name, dateOfCreated, dimens, bytes, typeOfFile);
        this.nameWithExtension = nameWithExtension;
    }

    public String getNameWithExtension() {
        return nameWithExtension;
    }

    public void setNameWithExtension(String nameWithExtension) {
        this.nameWithExtension = nameWithExtension;
    }

}
