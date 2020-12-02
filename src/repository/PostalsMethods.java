/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import conexion.ConexionC;
import models.Postals;

/**
 *
 * @author edubi
 */
public class PostalsMethods {

    public static PostalsMethods instance = null; // instancia de la clase MetodosGrafo

    /**
     *
     *
     * singleton para que exista únicamente una instacia de la clase
     * PostalsMethods
     *
     * @return la instancia única del objeto PostalsMethods
     */
    public static PostalsMethods getInstance() {
        if (instance == null) {
            instance = new PostalsMethods();
        }
        return instance;
    }

    private Postals inicio;

    /**
     * .
     *
     * método que inserta un postal en la lista
     *
     * @param textTop
     * @param textBellow
     * @param newName
     * @param size
     * @param font
     * @param pathOrigin
     *
     * @return "Insertado" o ""
     */
    public String insertPostal(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) {

        String pathPostal = "C:\\Users\\edubi\\OneDrive\\Pictures\\Postales\\";

        pathPostal = pathPostal.concat(newName);
        String[] outExtension;
        String str = newName.replace(".", " -");
        outExtension = str.split("-");
        Postals nuevo = new Postals(pathOrigin, pathPostal, outExtension[0],newName);
        if (inicio == null) {
            System.out.println("Entro aqui");
            inicio = nuevo;
            createPostal(pathOrigin, textTop, textBellow, newName, size, font);
            return "Insertado";
        }

    
        if (search(outExtension[0]) == null) {
            nuevo.sig = inicio; //insersion al inicio de una lista
            inicio = nuevo;
            createPostal(pathOrigin, textTop, textBellow, newName, size, font);
            return "Insertado";
        }
        
        return "Ya existe ese nombre";
    }

    private void createPostal(String pathImage, String textTop, String textBellow, String newName, String size, String font) {
        ConexionC connect = new ConexionC();
        if (textTop.isEmpty()) {
            textTop = "vacio";
        }
        if (textBellow.isEmpty()) {
            textBellow = "vacio";
        }
        connect.connect(pathImage, textTop, textBellow, newName, size, font);
    }
    
    public Postals search(String name){
        Postals aux = inicio;
        while (aux != null) {     
            if(aux.namePostal.equals(name)){
                return aux;
            }
            aux = aux.sig;
        }
        return null;
    } 
    
    
    public Postals  getPostals(){
        return inicio;
    }
}
