
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edubi
 * 
 * Clase de modelo para cuando se carga un plugin
 */
public class PluginsProjects implements Serializable{
    private String name;

    /**
     * Constructor de la clase, permite crear objetos de este tipo
     * @param name nombre del plugin
     */
    public PluginsProjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
