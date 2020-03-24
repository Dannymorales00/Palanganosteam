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

    public void leer(String[] args) {
        
    
        try{
            Wini ini = new Wini(new File("C:\\Users\\sdkca\\Desktop\\myinifile.ini"));
            int age = ini.get("owner", "age", int.class);
            double height = ini.get("owner", "height", double.class);
            String server = ini.get("database", "server");
            
            
            System.out.print("Edad: " + age + "\n");
            System.out.print("Altura: " + height + "\n");
            System.out.print("IP Servidor: " + server + "\n");
            // Para atrapar cual excepción relacionada a encontrar el archivo p. ej
            // (The system cannot find the file specified)
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    
    }
    
    
    
    
    
    
    
}
