/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author edubi
 *
 * Clase que permite darle un formarto a las imagenes originales que ingrese el
 * usuario
 */
public class Originals implements Serializable {

    private String path;
    private String name;
    private String dateOfCreated;
    private String dimens;
    private String bytes;
    private String typeOfFile;

    /**
     * Constructor  de la clase Originals, permite crear objetos de este tipo
     * @param path Dirreción de memoria donde se encuentra la imagen
     * @param name Nombre de la imagen
     * @param dateOfCreated Fecha en la que la imagen fue creada
     * @param dimens Dimeciones con las cuales cuenta la imagen
     * @param bytes Tamaño en KB de la imagen
     * @param typeOfFile  Tipo de imagen(png,jpg,bmp)
     */
    public Originals(String path, String name, String dateOfCreated, String dimens, String bytes, String typeOfFile) {
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
