/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Action;


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

    private ArrayList<PluginsProjects> listPlugins = new ArrayList<PluginsProjects>();
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

        String pathPostal = "C:\\\\Users\\\\edubi\\\\Documents\\\\Proyecto OO\\\\Proyecto4\\\\Postales\\\\";

        pathPostal = pathPostal.concat(newName);
        String[] outExtension;
        String str = newName.replace(".", " -");
        outExtension = str.split("-");
        Postals postal = new Postals(pathPostal, outExtension[0], "", " ", "",getTypeOfFile(newName), newName );
        Originals original = new Originals(pathOrigin, outExtension[0], getActualDate(pathOrigin), getDimens(pathOrigin),getBytes(pathOrigin),getTypeOfFile(pathOrigin));
        
        Project nuevo = new Project(outExtension[0], original, postal);
        if (inicio == null) {
            try {
                inicio = nuevo;
                createPostal(pathOrigin, textTop, textBellow, newName, size, font);
                Thread.sleep(2000);
                inicio.getPostal().setDateOfCreated(getActualDate(pathPostal));
                inicio.getPostal().setDimens(getDimens(pathPostal));
                inicio.getPostal().setBytes(getBytes(pathPostal));
                return "Insertado";
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        if (search(outExtension[0]) == null) {
            try {
                nuevo.setSig(inicio);  //insersion al inicio de una lista
                inicio = nuevo;
                createPostal(pathOrigin, textTop, textBellow, newName, size, font);
                Thread.sleep(2000);
                inicio.getPostal().setDateOfCreated(getActualDate(pathPostal));
                inicio.getPostal().setDimens(getDimens(pathPostal));
                inicio.getPostal().setBytes(getBytes(pathPostal));
                return "Insertado";
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private String getActualDate(String path) {

        File file = new File(path);
        String formatted = null;

        BasicFileAttributes attrs;
        try {
            attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            String pattern = "dd/MM/yyyy  HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            formatted = simpleDateFormat.format(new Date(time.toMillis()));
            System.out.println("La fecha y hora de creación del archivo es: " + formatted);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return objSDF.format(objDate);
       return formatted;
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
        File imgObj = new File(path);
        int imgLength = (int) imgObj.length();
        int kb = imgLength/1024;
        return String.valueOf(kb) + " kb";
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

        return "" + w + "x" + h;
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
    
    public void loadProjects()  {
        
        FileInputStream entrada = null;
        try {

            entrada = new FileInputStream("project.txt");
            ObjectInputStream leyendo = new ObjectInputStream(entrada);
            Project project = (Project) leyendo.readObject();
            
            inicio = project;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveProjects() {
        FileOutputStream fichero = null;
        try {
            fichero = new FileOutputStream("project.txt");
            ObjectOutputStream escribiendo = new ObjectOutputStream(fichero);
            escribiendo.writeObject(inicio);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String addPlugin(String pluginName) {
        PluginsProjects plugin = new PluginsProjects(pluginName);
        listPlugins.add(plugin);
        return "Insertado";
    }

    public ArrayList<PluginsProjects> getListPlugins() {
        return listPlugins;
    }
    
    public void savePlugins(){
         FileOutputStream fichero = null;
        try {
            fichero = new FileOutputStream("plugins.txt");
            ObjectOutputStream escribiendo = new ObjectOutputStream(fichero);
            escribiendo.writeObject(listPlugins);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void loadPlugins(){
            FileInputStream entrada = null;
        try {

            entrada = new FileInputStream("plugins.txt");
            ObjectInputStream leyendo = new ObjectInputStream(entrada);
            ArrayList<PluginsProjects> list = (ArrayList<PluginsProjects>) leyendo.readObject();       
            listPlugins = list;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
