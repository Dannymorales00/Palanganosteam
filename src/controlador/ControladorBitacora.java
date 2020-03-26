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
    private Statement sentencias = this.conn.getSentencias();
    private ResultSet datos = this.conn.getDatos();

    public ControladorBitacora() {
        this.conn = FrmMenu.getConexion();
        this.sentencias= conn.getSentencias();
        this.datos= conn.getDatos();
        
    }
 
    public boolean añadir(Bitacora bitacora){
        try {
            sentencias.executeUpdate("insert into bitacoras values(null,'"+bitacora.getPlaca()+"','"+bitacora.getProvincia()+"','"+bitacora.getDestino()+"','"+bitacora.getFechasalida()+"','"+bitacora.getHorasalida()+"','"+bitacora.getKinicial()+"','"+bitacora.getFechallegada()+"','"+bitacora.getHorallegada()+"','"+bitacora.getKfinal()+"')",sentencias.RETURN_GENERATED_KEYS);
            datos = sentencias.getGeneratedKeys();
            if (datos.next()) 
            {
                
               
                System.out.println(datos.getInt(2));
                System.out.println(datos.getInt(3));
         
            }   
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Error al añadir");
        }
        return false;
    }
    
    
    public Bitacora buscar(int placa){
        
        try {
            
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+placa);
            
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
            sentencias.executeUpdate("update into bitacora values(null,'"+bitacora.getPlaca()+"','"+bitacora.getProvincia()+"','"+bitacora.getDestino()+"','"+bitacora.getFechasalida()+"','"+bitacora.getHorasalida()+"','"+bitacora.getKinicial()+"','"+bitacora.getFechallegada()+"','"+bitacora.getHorallegada()+"','"+bitacora.getKfinal()+"')");
              
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar");
        }
        return false;
    }
    
    
    
    
        public ArrayList<Bitacora> listar(String placa){
            ArrayList<Bitacora> bitacoras = new ArrayList();
            try {
                this.datos = this.sentencias.executeQuery("select * from bitacora where descripcion like '"+placa+"'");
                
                while(datos.next())
            {
              
               Bitacora bitacora = new Bitacora(datos.getString(2),datos.getString(3),datos.getString(4),datos.getDate(5),datos.getTime(6),datos.getInt(7),datos.getDate(8),datos.getTime(9),datos.getInt(10));
               
               bitacoras.add(bitacora);
            
            }
    
            } catch (SQLException ex) {
                System.out.println("Error al listar");
            }
            return bitacoras; 
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
        
        try {
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+bitacora.getPlaca());
                
            if (datos.next()) {
                return true;
            }
                
        } catch (SQLException ex) {
            System.out.println("Error al validar");
        }
        return false;
    }      
        
       
    
}
