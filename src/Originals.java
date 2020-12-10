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
public class Originals extends Images implements Serializable{

    public Originals(String path, String name, String dateOfCreated, String dimens, String bytes, String typeOfFile) {
        super(path, name, dateOfCreated, dimens, bytes, typeOfFile);
    }
}
