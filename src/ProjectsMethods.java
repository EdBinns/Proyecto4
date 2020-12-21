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
    private PluginsProjects pluginSelected;

   

    /**
     * .
     *
     * método que inserta un postal en la lista simple
     * @param textTop  Texto de la parte de arriba de la postal
     * @param textBellow Texto de la parte de abajo de la postal
     * @param newName Nombre de la postal con extension
     * @param size Tamaño de la letra
     * @param font Fuente de la  letra
     * @param pathOrigin Dirreción de memoria de la imagen original
     *
     * @return "Insertado" o ""
     */
    public String insertPostal(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) throws FileNotFoundException {

        //Path donde se va a guardar la imagen
        String pathPostal = "C:\\Users\\edubi\\Documents\\Proyecto OO\\Proyecto4\\Postales\\";
        String pathSave =  pathPostal;
        pathPostal = pathPostal.concat(newName);
        String[] outExtension;
        String str = newName.replace(".", " -");
        outExtension = str.split("-");
        //Se crea el objeto postal
        Postals postal = new Postals(pathPostal, outExtension[0], "", " ", "",getTypeOfFile(newName), newName );
        //Se crea el objeto Originals
        Originals original = new Originals(pathOrigin, outExtension[0], getActualDate(pathOrigin), getDimens(pathOrigin),getBytes(pathOrigin),getTypeOfFile(pathOrigin));
        
        //Se crea un objeto nuevo para ingresarlo a la lista
        Project nuevo = new Project(outExtension[0], original, postal);
        //Se verifica que el inicio este vasio
        if (inicio == null) {
            try {
                inicio = nuevo;
                createPostal(pathOrigin, textTop, textBellow, newName, size, font,pathSave);
                Thread.sleep(2000);
                inicio.getPostal().setDateOfCreated(getActualDate(pathPostal));
                inicio.getPostal().setDimens(getDimens(pathPostal));
                inicio.getPostal().setBytes(getBytes(pathPostal));
                return "Insertado";
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectsMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        //Se verifica que el nombre no este repetido y se agrega a la lista en caso positivo
        if (search(outExtension[0]) == null) {
            try {
                nuevo.setSig(inicio);  //insersion al inicio de una lista
                inicio = nuevo;
                createPostal(pathOrigin, textTop, textBellow, newName, size, font,pathSave);
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

    /**
     * Realiza la conexion con el projecto en C llamando a la clase conexionC
     * 
     * @param textTop  Texto de la parte de arriba de la postal
     * @param textBellow Texto de la parte de abajo de la postal
     * @param newName Nombre de la postal con extension
     * @param size Tamaño de la letra
     * @param font Fuente de la  letra
     * @param pathImage Dirreción de memoria de la imagen original
     * @param  pathSave   Dirreción de memoria donde se va a guardar
     * */
    private void createPostal(String pathImage, String textTop, String textBellow, String newName, String size, String font,String pathSave) {
        ConexionC connect = new ConexionC();
        if (textTop.isEmpty()) {
            textTop = "vacio";
        }
        if (textBellow.isEmpty()) {
            textBellow = "vacio";
        }
        connect.connect(pathImage, textTop, textBellow, newName, size, font,pathSave);
    }

    /**
     * Obtiene la fecha de creación de las imagenes 
     * @param path dirreción de memoria de la imagen
     * @return fecha de cración
     */
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
    
    /**
     * Busca una  Project en especifico según su nombre
     * @param name nombre del Project
     * @return  Project encontrado
     */
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
    
    /**
     * Devuelve el inicio de la lista
     * @return inicio de la lista simple
     */
    public Project  getProjects(){
        return inicio;
    }
   
    /**
     * Setea el último projecto seleccionado del usuario
     * @param temp projecto seleccionado
     */
     public void setLastProjectSee(Project temp){
        lastPostalSee = temp;
    }
    
       /**
     * Obtiene el último projecto seleccionado del usuario
     * @return  projecto seleccionado
     */
    public Project getLastPostalSee(){
        return lastPostalSee;
    }
/**
 * Obtiene el peso en KB de una imagen 
 * @param path dirrecion de memoria de la imagen
 * @return KB de la imagen
 */
    private String getBytes(String path) {

        //Se obtiene el tamaño de una imagen
        File imgObj = new File(path);
        int imgLength = (int) imgObj.length();
        int kb = imgLength/1024;
        return String.valueOf(kb) + " kb";
    }
    /**
     * Obtiene las dimensiones de la imagen
     * @param path Dirrecion de memoria de la imagen
     * @return dimensiones
     * @throws FileNotFoundException 
     */
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
    /**
     * Obtiene el tipo de archivo de la imagen
     * @param name nombre del archivo
     * @return tipo de archivo
     */
    private String getTypeOfFile(String  name){
          
            if (name.contains("png")) {
                return "Archivo png";
            } else if (name.contains("jpg")) {
                return "Archivo jpg";
            } else {
               return "Archivo bmp";
            }
    }
    /**
     * Carga la lista simple del archivo y se guarda en el objeto inicio
     */
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
/**
 * Guarda el inicio de la lista simple en un archivo
 */
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
    
    /**
     * Obtiene el ultimo plugin seleccionado
     * @return  Plugin
     */
     public PluginsProjects getPluginSelected() {
        return pluginSelected;
    }

     /**
      * Setea el ultimo plugin seleccionado
      * @param pluginSelected  Plugin seleccioando
      */
    public void setPluginSelected(PluginsProjects pluginSelected) {
        this.pluginSelected = pluginSelected;
    }

    /**
    *Agrega un plugin a la lista 
    *@param pluginName nombre del plugin
    */
    public String addPlugin(String pluginName) {
        PluginsProjects plugin = new PluginsProjects(pluginName);
        listPlugins.add(plugin);
        return "Insertado";
    }
  
    /**
     * Borra un plufig de la lista
     * @param plugin plugin a borrar
     * @return Mensaje de exito
     */
    public String deletePlugin(PluginsProjects plugin) {
        if(plugin != null){
               listPlugins.remove(plugin);
        return "Plugin Eliminado";
        }
        return "No existe el plugin a eliminar";
    }
    /**
     * Busca un plugin en especifico
     * @param name nombre del plugin
     * @return plugin 
     */
      public PluginsProjects searchPlugin(String name){
         for (PluginsProjects object : listPlugins) {
             if(object.getName().equals(name)){
                 return object;
             }
        }
         return  null;
    }

      /**
       * Obtiene la lista de los plugins
       * @return  lista de plugins
       */
    public ArrayList<PluginsProjects> getListPlugins() {
        return listPlugins;
    }
    
    /**
     * Guarda la lista de plugins dentro de un archivo
     *
     */
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
        } catch (Exception ex) {
            Logger.getLogger(postalsUI.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Carga la lista de los plugins
     */
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
        }catch (Exception ex) {
            Logger.getLogger(postalsUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
