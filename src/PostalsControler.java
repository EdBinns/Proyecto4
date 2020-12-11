/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


/**
 *
 * @author edubi
 */
public class PostalsControler {

    ProjectsMethods pm = ProjectsMethods.getInstance();


    public Project getProjects() {
        return  pm.getProjects();       
    }


    public String callPostalsService(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) {
        try {
            return pm.insertPostal(pathOrigin, textTop, textBellow, newName, size, font);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostalsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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

    public Project searchProject(String name) {     
        return pm.search(name);
    }
  
    public void setLastPostalSee(Project temp) {
        if (temp != null) {
            pm.setLastProjectSee(temp);
        }
    }
    
    public Postals getPostal(){
        return pm.getLastPostalSee().getPostal();
    }
     
    public Originals getOriginals(){
        return pm.getLastPostalSee().getOriginal();
    }
    
    public void load(){
        pm.loadProjects();
    }
    
    public void save(){
        pm.saveProjects();
    }

    public String savePlugin(String name){
        return  pm.addPlugin(name);
    }
    
    public String deletePlugin(PluginsProjects plugin){
        return pm.deletePlugin(plugin);
    }
    public DefaultListModel<String> showPlugins() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (PluginsProjects object : pm.getListPlugins()) {
            listModel.addElement(object.getName());
        }
        return listModel;
    }
    
    public PluginsProjects searchPlugin(String name){
       return pm.searchPlugin(name);
    }
    
    public void savePlugins(){
    
        pm.savePlugins();
    }
    
    public void loadPlugins(){
        pm.loadPlugins();
    }
    
  public PluginsProjects getPluginSelected() {
        return pm.getPluginSelected();
    }

    public void setPluginSelected(PluginsProjects pluginSelected) {
        pm.setPluginSelected(pluginSelected);
    }
}
