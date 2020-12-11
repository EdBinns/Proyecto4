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
 * Clase principal del projecto, en esta se almacena el nombre del proyecto creado, la postal generada y la imagen original 
 */
public class Project implements Serializable{
    
    private String nameProject;
    private Originals original;
    private Postals postal;

    //Atributo que indica si hay mas Objetos dentro de la lista simple
    private Project sig;


    /**
     * Constructor de la clase Project
     * @param nameProject Nombre del projecto
     * @param original Objeto que contiene la información de la imagen original
     * @param postal  Objeto que contiene la informacuón de la postal
     */
    public Project(String nameProject, Originals original, Postals postal) {
        this.nameProject = nameProject;
        this.original = original;
        this.postal = postal;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Originals getOriginal() {
        return original;
    }

    public void setOriginal(Originals original) {
        this.original = original;
    }

    public Postals getPostal() {
        return postal;
    }

    public void setPostal(Postals postal) {
        this.postal = postal;
    }
      public Project getSig() {
        return sig;
    }

    public void setSig(Project sig) {
        this.sig = sig;
    }
    
}
