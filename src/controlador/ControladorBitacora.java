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
import modelo.Bitacora;


/**
 *
 * @author Danny_PC
 */




public class ControladorBitacora {
    
    private Conexion conn;
    private Statement sentencias = this.conn.getSentencias();
    private ResultSet datos = this.conn.getDatos();

    public ControladorBitacora(Conexion conn) {
        this.conn = conn;
    }
 
    
       public boolean añadir(Bitacora bitacora){
        try {
            sentencias.executeUpdate("insert into bitacoras values(null,'"+bitacora.getPlaca()+"','"+bitacora.getProvincia()+"','"+bitacora.getDestino()+"','"+bitacora.getFechasalida()+"','"+bitacora.getHorasalida()+"','"+bitacora.getKinicial()+"','"+bitacora.getFechallegada()+"','"+bitacora.getHorallegada()+"','"+bitacora.getKfinal()+"')",sentencias.RETURN_GENERATED_KEYS);
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
    
    
    
}
