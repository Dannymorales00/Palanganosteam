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

/**
 *
 * @author Danny_PC
 */
public class ControladorVehiculo {;
        
    private Conexion conn;
    private Statement sentencias = this.conn.getSentencias();
    private ResultSet datos = this.conn.getDatos();

    public ControladorVehiculo(Conexion conn) {
        this.conn = conn;
    }
        
    public boolean añadir(Vehiculo vehiculo){
        try {
            sentencias.executeUpdate("insert into vehiculos values(null,'"+vehiculo.getPlaca()+"','"+vehiculo.getDescripcion()+"')",sentencias.RETURN_GENERATED_KEYS);
            datos = sentencias.getGeneratedKeys();
            if (datos.next()) {
                System.out.println(datos.getInt(1));
            }   
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al añadir");
        }
        return false;
    }
        
    public Vehiculo buscar(int placa){
            try {
            
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+placa);
            
            if(datos.next())
            {
                System.out.println(datos.getInt(1));
                System.out.println(datos.getString(2));
                System.out.println(datos.getString(3));
            
            }else 
                return null;
            }
            catch (SQLException ex) {
                System.out.println("Error al buscar");
            }
            return null;
    } 
        
    public boolean actualizar(Vehiculo vehiculo){
            try {
            sentencias.executeUpdate("insert into vehiculos values(null,'"+vehiculo.getPlaca()+"','"+vehiculo.getDescripcion()+"')");
              
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
            ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
            try {
                this.datos = this.sentencias.executeQuery("select * from vehiculos where descripcion like '"+descripcion+"'");
                
                while(datos.next())
            {
               Vehiculo vehiculo = new Vehiculo(datos.getString(2),datos.getString(3));
               
               vehiculos.add(vehiculo);
            
            }
    
            } catch (SQLException ex) {
                System.out.println("Error al listar");
            }
           return vehiculos; 
    }
        
    public boolean ValidarPK(Vehiculo vehiculo){
        
        try {
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+vehiculo.getPlaca());
                
            if (datos.next()) {
                return true;
            }
                
        } catch (SQLException ex) {
            System.out.println("Error al validar");
        }
        return false;
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
         
}
