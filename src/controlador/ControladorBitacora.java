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
import modelo.Bitacora;
import ventanas.FrmMenu;


/**
 *
 * @author Danny_PC
 */




public class ControladorBitacora {
    
    private Conexion conn;
    private Statement sentencias;
    private ResultSet datos; 

    public ControladorBitacora() {
        this.conn = FrmMenu.getConexion();
        this.sentencias= conn.getSentencias();
        this.datos= conn.getDatos();
        
    }
 
    public boolean añadir(Bitacora bitacora){
        try 
        {
            sentencias.executeUpdate("insert into bitacora values(NULL,'"+bitacora.getPlaca()+"','"+bitacora.getProvincia()+"','"+bitacora.getDestino()+"',CURDATE(),CURTIME(),'"+bitacora.getKinicial()+"',NULL,NULL,NULL)");
     
            return true;
            
        }catch (Exception e) 
            {
            System.out.println("Error al añadir una bitacora");
            }
        return false;
    }
    
    
    public Bitacora buscar(int placa){
        
        try {
            
            this.datos = this.sentencias.executeQuery("select * from bitacora where placa="+placa);
            
            if(datos.next())
            {
                System.out.println(datos.getString(2));
                System.out.println(datos.getString(3));
                System.out.println(datos.getString(4));
                System.out.println(datos.getDate(5));
                System.out.println(datos.getTime(6));
                System.out.println(datos.getInt(7));
                System.out.println(datos.getDate(8));
                System.out.println(datos.getTime(9));
                System.out.println(datos.getInt(10));
                
                Bitacora bitacora = new Bitacora();
                bitacora.setPlaca(datos.getString(2));
                bitacora.setProvincia(datos.getString(3));
                bitacora.setDestino(datos.getString(4));
                bitacora.setFechasalida(datos.getDate(5));
                bitacora.setHorasalida(datos.getTime(6));
                bitacora.setKinicial(datos.getInt(7));
                bitacora.setFechallegada(datos.getDate(8));
                bitacora.setHorallegada(datos.getTime(9));
                bitacora.setKfinal(datos.getInt(10));
                return bitacora;
            }
                
            }catch (SQLException ex) {
                System.out.println("Error al buscar");
            }
            return null;
    } 
    
    
    
    public boolean eliminar(Bitacora bitacora){
        try {
            this.sentencias.executeUpdate("delete from bitacora where placa="+bitacora.getPlaca());
            
            
        } catch (SQLException ex) {
                
            System.out.println("Error al borrar");
        }
            return false;
    }
    
    
    public boolean actualizar(Bitacora bitacora){
            try {  
              
            this.sentencias.executeUpdate("UPDATE bitacora SET fechallegada='CURDATE()',horallegada='CURTIME()',kfinal='"+bitacora.getKfinal()+"' WHERE placa ='"+bitacora.getPlaca()+"';");
              
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar");
        }
        return false;
    }
    
    
    
    
    public ArrayList<Bitacora> listar(String placa){
            ArrayList<Bitacora> bitacoras = new ArrayList();
            try {
                this.datos = this.sentencias.executeQuery("select * from bitacora where placa='"+placa+"'");
                
                while(datos.next()){
              
               Bitacora bitacora = new Bitacora(datos.getString(2),datos.getString(3),datos.getString(4),datos.getDate(5),datos.getTime(6),datos.getInt(7),datos.getDate(8),datos.getTime(9),datos.getInt(10));
               
               bitacoras.add(bitacora);
               
            }
                return bitacoras;
            } catch (SQLException ex) {
                System.out.println("Error al listar");
            }
            return null; 
    }
    
        
        
        
        
    public boolean ValidarPK(Bitacora bitacora){
        
        try {
            this.datos = this.sentencias.executeQuery("select * from bitacora where placa="+bitacora.getPlaca());
                
            if (datos.next()) {
                return false;
            }
                
        } catch (SQLException ex) {
            System.out.println("Error al validar");
        }
        return true;
    } 
        
        
    public boolean ValidarFK(Bitacora bitacora){
        boolean result = false;
        try 
        {
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+bitacora.getPlaca());
                
            if (datos.next()) 
            {
                System.out.println("si existe el vehiculo FK");
                result=true;
              
            }
                
        } catch (SQLException ex){
            System.out.println("Error al validar");
            
        }
         
        return result;
       
    }      
        
       
    
}
