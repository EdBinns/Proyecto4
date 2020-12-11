/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author edubi
 *
 * Clase que permite al conexión entre la vista y la clase donde se obtiene los
 * datos
 */
public class PostalsControler {

    //Singlieton de la clase projectsMethds
    ProjectsMethods pm = ProjectsMethods.getInstance();

    //Obtiene el inicio de la lista simple 
    public Project getProjects() {
        return pm.getProjects();
    }

    /**
     * Funcion que comunica vista y la fuente de datos para poder crear una
     * nueva postal
     *
     * @param pathOrigin Dirreciön de memoria donde se ubica la iamgen original
     * @param textTop Texto que va en la parte de arriba de la imagen
     * @param textBellow que va en la parte de abajo de la imagen
     * @param newName Nombre y extencion de la postal que haya ingresado el
     * usuario
     * @param size Tamaño de la letra
     * @param font Fuente de la letra
     * @return Mensaje si fue insertado o no
     */
    public String callPostalsService(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) {
        try {
            return pm.insertPostal(pathOrigin, textTop, textBellow, newName, size, font);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostalsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Funcion que permite cargar los nombres de los projectos dentro de un
     * listmodel
     *
     * @return listmodel con todos los nombres de los projectos
     */
    public DefaultListModel<String> showPostals() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Project aux = getProjects();
        while (aux != null) {
            listModel.addElement(aux.getNameProject());
            System.out.println(aux.getNameProject());
            aux = aux.getSig();
        }
        return listModel;
    }

    /**
     * Comunica la vista y la fuente de datos para poder buscar un projecto en
     * especifico
     *
     * @param name Nombre del projecto a buscar
     * @return el projecto encontrado
     */
    public Project searchProject(String name) {
        return pm.search(name);
    }

    /**
     * Setea el ultimo projecto que el usuario haya seleccionado
     *
     * @param temp projecto seleccionado
     */
    public void setLastPostalSee(Project temp) {
        if (temp != null) {
            pm.setLastProjectSee(temp);
        }
    }

    /**
     * Devuelve la postal del ultimo projecto que el usuario haya seleccionado
     *
     * @return temp projecto seleccionado
     */
    public Postals getPostal() {
        return pm.getLastPostalSee().getPostal();
    }

    /**
     * Devuelve el objeto Original del ultimo projecto que el usuario haya
     * seleccionado
     *
     * @return temp projecto seleccionado
     */
    public Originals getOriginals() {
        return pm.getLastPostalSee().getOriginal();
    }

    /**
     * Carga todos los projectos que sean creado
     */
    public void load() {
        pm.loadProjects();
    }

    /**
     * Guarda todos los projectos que sean creado
     */
    public void save() {
        pm.saveProjects();
    }

    /**
     * Funcion que permite cargar un plugin de forma dinamica
     */
    public void loadPluginInProject() {
        PluginsProjects plugin = getPluginSelected();
        JavaClassLoader jcl = new JavaClassLoader();
        System.out.println(plugin.getName());
        jcl.invokeClassMethod(plugin.getName(), getPostal().getPath());
    }

    /**
     * Funcion que premite guardar un plugin que se haya cargado
     *
     * @param name nombre del plugin
     * @return mensaje de exito o fracaso
     */
    public String savePlugin(String name) {
        return pm.addPlugin(name);
    }

    /**
     * Elimina un plugin de la lista
     *
     * @param plugin plugin a eliminar
     * @return mensaje de exito o fracaso
     */
    public String deletePlugin(PluginsProjects plugin) {
        return pm.deletePlugin(plugin);
    }

    /**
     * Permite cargar los nombres de los plugins dentro de un listmodel
     *
     * @return listmodel con los nombres cargados
     */
    public DefaultListModel<String> showPlugins() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (PluginsProjects object : pm.getListPlugins()) {
            listModel.addElement(object.getName());
        }
        return listModel;
    }

    /**
     * Busca un plugin en especifico dentro de la lista
     *
     * @param name nombre del plugin a buscar
     * @return plugin encontrado
     */
    public PluginsProjects searchPlugin(String name) {
        return pm.searchPlugin(name);
    }

    /**
     * Guarda todos los plugins cargados en un archivo
     */
    public void savePlugins() {
        pm.savePlugins();
    }

    /**
     * Carga todos los plugins cargados en un archivo
     */
    public void loadPlugins() {
        pm.loadPlugins();
    }

    /**
     * Obtiene el plugin que haya seleccionado el usuario
     *
     * @return plugin seleccionado
     */
    public PluginsProjects getPluginSelected() {
        return pm.getPluginSelected();
    }

    /**
     * Setea el plugin que haya seleccionado el usuario
     *
     * @return plugin seleccionado
     */
    public void setPluginSelected(PluginsProjects pluginSelected) {
        pm.setPluginSelected(pluginSelected);
    }

    /**
     * Crea una copia del .class del plugin dentro del proyecto, para posteriormente cargarlo de forma dianmica
     * @param source origen del archivo
     * @param dest destino donde se va a guardar el plugin
     * @throws IOException mensaje de error
     */
    public void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    /**
     * Copia archivos extra que ocupe el plugin dentro del proyecto
     *  @param source origen del archivo
     * @param destination destino donde se va a guardar el plugin 
     */
    public void copyFolder(File source, File destination) {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdirs();
            }

            String files[] = source.list();

            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);

                copyFolder(srcFile, destFile);
            }
        } else {
            InputStream in = null;
            OutputStream out = null;

            try {
                in = new FileInputStream(source);
                out = new FileOutputStream(destination);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            } catch (Exception e) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
