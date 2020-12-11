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
 * Clase modelo para las postales creadas, esta hereda de Originals ya que comparten los atributos
 */
public class Postals extends Originals implements Serializable{
   
    private String nameWithExtension;

    /**
     * Constructor  de la clase Postal, permite crear objetos de este tipo
     * @param path Dirreción de memoria donde se encuentra la imagen
     * @param name Nombre de la imagen
     * @param dateOfCreated Fecha en la que la imagen fue creada
     * @param dimens Dimeciones con las cuales cuenta la imagen
     * @param bytes Tamaño en KB de la imagen
     * @param typeOfFile  Tipo de imagen(png,jpg,bmp)
     * @param nameWithExtension Nombre con la extension de la imagen
     */
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
