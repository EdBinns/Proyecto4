

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;

/**
 *
 * @author edubi
 */
public class ConexionC {
    
    /**
     * Función que permite realizar la conexión con el programa en C
     * 
     * @param path dirreción de memoria donde se encuentra la imagen a editar
     * @param textTop texto que haya introducido el usuario para la parte de arriba de la imagen ,puede estar nulo
     * @param textBellow  texto que haya introducido el usuario para la parte de abajo de la imagen ,puede estar nulo
     * @param newName  Nombre que quiera el usuario ponerle a la postal, debe ir con la extensión 
     * @param size Tamaño de la letra que seleccione el usuario, puede ser pequeña, mediana, grande
     * @param font  Tipo de fuente que seleccione el usuario.
     */
    public void connect(String path, String textTop, String textBellow, String newName, String size, String font) {
        try {
            Process process = new ProcessBuilder("Ejecutable de C\\Proyecto.exe",
                    path, textTop,textBellow,newName,size,font).start();
            System.out.println(process.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
