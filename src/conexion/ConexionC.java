package conexion;

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

    public void connect(String path, String textTop, String textBellow, String newName, String size, String font) {
        try {
            Process process = new ProcessBuilder("C:\\Users\\edubi\\Documents\\ProyectoLenguajes\\Proyecto\\cmake-build-debug\\proyecto.exe",
                    path, textTop,textBellow,newName,size,font).start();
            System.out.println(process.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
