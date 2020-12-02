/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.swing.DefaultListModel;
import models.Postals;
import repository.PostalsMethods;

/**
 *
 * @author edubi
 */
public class PostalsControler {

    PostalsMethods pm = PostalsMethods.getInstance();



    public Postals getPostals() {
        return  pm.getPostals();       
    }


    public String callPostalsService(String pathOrigin, String textTop, String textBellow, String newName, String size, String font) {
        return pm.insertPostal(pathOrigin, textTop, textBellow, newName, size, font);
    }

    public DefaultListModel<String> showPostals() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Postals aux = getPostals();
        while (aux != null) {
       
            listModel.addElement(aux.namePostal);
            aux = aux.sig;
        }
        return listModel;
    }

    public Postals searchPostals(String name) {
        
        return pm.search(name);
    }
}
