/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import conexion.ConexionC;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import models.Originals;
import models.Postals;
import models.Project;

/**
 *
 * @author edubi
 */
public class ProjectsMethods {

    public static ProjectsMethods instance = null; // instancia de la clase MetodosGrafo

    /**
     *
     *
     * singleton para que exista únicamente una instacia de la clase
     * PostalsMethods
     *
     * @return la instancia única del objeto PostalsMethods
     */
    public static ProjectsMethods getInstance() {
        if (instance == null) {
            instance = new ProjectsMethods();
        }
        return instance;
    }

    private Project inicio;
    private Project lastPostalSee;

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
    public String insertPostal(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) throws FileNotFoundException {

        String pathPostal = "C:\\Users\\edubi\\OneDrive\\Pictures\\Postales\\";

        pathPostal = pathPostal.concat(newName);
        String[] outExtension;
        String str = newName.replace(".", " -");
        outExtension = str.split("-");
        Postals postal = new Postals(pathPostal, outExtension[0], getActualDate(),getDimens(pathPostal), getBytes(pathPostal),getTypeOfFile(newName), newName );
        Originals original = new Originals(pathOrigin, outExtension[0], " ", getDimens(pathOrigin),getBytes(pathOrigin),getTypeOfFile(pathOrigin));
        
        Project nuevo = new Project(outExtension[0], original, postal);
        if (inicio == null) {
            inicio = nuevo;
            createPostal(pathOrigin, textTop, textBellow, newName, size, font);
            return "Insertado";
        }

    
        if (search(outExtension[0]) == null) {
            nuevo.setSig(inicio);  //insersion al inicio de una lista
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

    private String getActualDate() {
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 
 
        System.out.println(objDate); 
        String strDateFormat = "dd/MMM/yyyy  hh:mm:ssa "; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto 
        System.out.println(objSDF.format(objDate)); // El formato de fecha se aplica a la fecha actual
   
        return objSDF.format(objDate);
    }
    public Project search(String name){
        Project aux = inicio;
        while (aux != null) {     
            if(aux.getNameProject().equals(name)){
                return aux;
            }
            aux = aux.getSig();
        }
        return null;
    } 
    
    
    public Project  getProjects(){
        return inicio;
    }
   
     public void setLastProjectSee(Project temp){
        lastPostalSee = temp;
    }
    
    public Project getLastPostalSee(){
        return lastPostalSee;
    }

    private String getBytes(String path) {

        //Se obtiene el tamaño de una imagen
        File imgObj = new File(path);;
        int imgLength = (int) imgObj.length();
        return String.valueOf(imgLength);
    }
    
    private String getDimens(String path) throws FileNotFoundException {
      
        ImageInputStream iis;
        int w = 0;
        int h = 0;
        try {
            System.out.println(path);
            FileInputStream is = new FileInputStream(path);
            iis = ImageIO.createImageInputStream(is);
            BufferedImage image = ImageIO.read(iis);
            w = image.getWidth();
            h = image.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "" + h + "x" + w;
    }
    
    private String getTypeOfFile(String  name){
          
            if (name.contains("png")) {
                return "Archivo png";
            } else if (name.contains("jpg")) {
                return "Archivo jpg";
            } else {
               return "Archivo bmp";
            }
    }
}
