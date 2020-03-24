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

    
    
    
 /**
 *lee el archivo config.ini y
 * retorna un vector ==> motor-basededatos-servidor-usuario-contraseña
 */
    
    public String[] leer() {
        String vect[]=new String[5];
    
        try{
             //obtenemos la ubicacion del archivo y quitamos los caracteres del inicio inecesarios.
             String ruta= (String.valueOf( ClassLoader.getSystemResource("archivoconfig/config.ini")).substring(6)) ;
             System.out.println("ruta al leer:"+ruta);
            
            
            Wini ini = new Wini(new File(ruta));
            String servidor = ini.get("database", "servidor", String.class);
            String usuario = ini.get("database", "usuario", String.class);
            String contraseña = ini.get("database", "contraseña", String.class);
            String motor = ini.get("database", "motor", String.class);
            String basededatos = ini.get("database", "basededatos", String.class);
        
            
            System.out.println("motor: "+ motor);
            System.out.println("basededatos: "+ basededatos);
            System.out.println("servidor: "+ servidor);
            System.out.println("usuario: "+ usuario);
            System.out.println("contraseña: "+ contraseña);
            
           
            
            vect[0]= motor;
            vect[1]= basededatos;
            vect[2]= servidor;
            vect[3]= usuario;
            vect[4]= contraseña;
            return vect;

        // Para atrapar cual excepción relacionada a encontrar el archivo 
        }catch(Exception e){
            System.out.println("error al leer el archivo config.ini");
            System.err.println(e.getMessage());
        }
        return null;
    
    }
    
    
    
    
    
    
    
}