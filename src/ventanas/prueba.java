/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;



import conexion.Conexion;
import controlador.ControladorVehiculo;
import java.util.ArrayList;
import modelo.Vehiculo;

/**
 *
 * @author Danny_PC
 */
public class prueba {
    
     private static ArrayList<Vehiculo> vehiculos= new ArrayList();
     private static ControladorVehiculo cv;
     private static Conexion conn = new Conexion();
    /**
     * @param args the command line arguments
     */
  
     
     
     public static void main(String[] args) {
         
         
         conn.Conectar();
         cv= new ControladorVehiculo(conn);
         
         vehiculos = cv.listar("toyota");
           
         
         if(vehiculos.isEmpty())
            {
                System.out.println("esta vacio");
            }else
            {
                for(int i=0;i<vehiculos.size();i++)
                {
                    System.out.println(vehiculos.get(i).getPlaca());
                    System.out.println(vehiculos.get(i).getDescripcion());
                
                }
            
            
            }
        
        
    }

}
