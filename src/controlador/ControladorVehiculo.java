/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Vehiculo;
import ventanas.FrmMenu;

/**
 *
 * @author Danny_PC
 */
public class ControladorVehiculo {;
        
    private Conexion conn;
    private Statement sentencias;
    private ResultSet datos;

    public ControladorVehiculo() {
       
       conn = FrmMenu.getConexion();
       this.sentencias= conn.getSentencias();
       this.datos=conn.getDatos();
    }

    public ControladorVehiculo(Conexion conn) {
        this.conn = conn;
        this.sentencias= conn.getSentencias();
        this.datos=conn.getDatos();
    }
    
    
    
    
        
    public boolean añadir(Vehiculo vehiculo){
        try {
           return sentencias.execute("insert into vehiculos values(null,'"+vehiculo.getPlaca()+"','"+vehiculo.getDescripcion()+"')",sentencias.RETURN_GENERATED_KEYS);
             
        } catch (SQLException ex) {
            System.out.println("Error al añadir");
        }
        return false;
    }
        
    public Vehiculo buscar(String placa){
        try {
            
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+placa);
            
                if(datos.next())
                {
             
                    System.out.println(datos.getString(2));
                    System.out.println(datos.getString(3));
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setPlaca(datos.getString(2));
                    vehiculo.setDescripcion(datos.getString(3));
                    
                    
                    return vehiculo;
                } 
                
        }catch (SQLException ex) {
                System.out.println("Error al buscar");
            }
        return null;

    } 
        
    public boolean actualizar(Vehiculo vehiculo){
            try {
            sentencias.executeUpdate("UPDATE vehiculos SET descripcion='"+vehiculo.getDescripcion()+"' WHERE placa ='"+vehiculo.getPlaca()+"';");
              
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar");
        }
        return false;
    }
        
    public boolean eliminar(Vehiculo vehiculo){
            try {
            this.sentencias.executeUpdate("delete from vehiculos where placa="+vehiculo.getPlaca());
            
            
            } catch (SQLException ex) {
                System.out.println("Error al borrar");
            }
            return false;
    }
     
    
    
    public ArrayList<Vehiculo> listar(String descripcion){
            ArrayList<Vehiculo> vehiculos = new ArrayList();
            try {
                this.datos = this.sentencias.executeQuery("select * from vehiculos where descripcion='"+descripcion+"'");
                
                while(datos.next())
                {
                     Vehiculo vehiculo = new Vehiculo(datos.getString(2),datos.getString(3));
               
                    vehiculos.add(vehiculo);
            
            }
                return vehiculos; 
            } catch (SQLException ex) {
                System.out.println("Error al listar");
            }
           return null; 
    }
        
    public boolean ValidarPK(Vehiculo vehiculo){
        
        try {
            return this.sentencias.execute("select * from vehiculos where placa="+vehiculo.getPlaca());
              
                
        } catch (SQLException ex) {
            System.out.println("Error al validar");
        }
        return true;
    }
       
    public ArrayList<Vehiculo> todosLosVehiculos(){
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try {
            this.datos = this.sentencias.executeQuery("select * from vehiculos");
            
            while(datos.next()){
                vehiculos.add(new Vehiculo(datos.getString(2),datos.getString(3)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculos;
    }
    
    public boolean ValidarFK(){
     return true;
    }
         
}
