/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author edubi
 */
public class Main {
    
    public static void main(String[] args) {
        //Permite cargar de un archivo de testo las imagenes que esten creadas dentro del proyecto
        PostalsControler pc = new PostalsControler();
        pc.load();
        
        //Abre el frame principal de la aplicación
        menu frame = new menu();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
