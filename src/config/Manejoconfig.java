/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.io.File;
import org.ini4j.*;
/**
 *
 * @author Danny_PC
 */
public class Manejoconfig {

    public void leer() {
        
    
        try{
             String ruta= (String.valueOf( ClassLoader.getSystemResource("archivoconfig/config.ini")).substring(6)) ;
             System.out.println("aqui ruta:"+ruta);
            
            
            Wini ini = new Wini(new File(ruta));
            String servidor = ini.get("database", "servidor", String.class);
            String usuario = ini.get("database", "usuario", String.class);
            String contraseña = ini.get("database", "contraseña", String.class);
            String motor = ini.get("database", "motor", String.class);
            String basededatos = ini.get("database", "basededatos", String.class);
        
            
            
            System.out.println("servidor: "+ servidor);
            System.out.println("usuario: "+ usuario);
            System.out.println("contraseña: "+ contraseña);
            System.out.println("motor: "+ motor);
            System.out.println("basededatos: "+ basededatos);
            // Para atrapar cual excepción relacionada a encontrar el archivo p. ej
            // (The system cannot find the file specified)
        }catch(Exception e){
            System.out.println("error al leer el archivo config.ini");
            System.err.println(e.getMessage());
        }
    
    }
    
    
    
    
    
    
    
}
