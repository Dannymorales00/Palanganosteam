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
            System.out.println("placa:"+ bitacora.getPlaca());
            System.out.println("provincia:"+ bitacora.getProvincia());
            System.out.println("destino:"+ bitacora.getDestino());
            System.out.println("Kinicial:"+bitacora.getKinicial());
            sentencias.executeUpdate("insert into bitacora values(NULL,'"+bitacora.getPlaca()+"','"+bitacora.getProvincia()+"','"+bitacora.getDestino()+"',CURDATE(),CURTIME(),'"+bitacora.getKinicial()+"',NULL,NULL,NULL)");
     
            return true;
            
        }catch (Exception e) 
            {
            System.out.println("Error al añadir una bitacora");
            }
        return false;
    }
    
    
    public Bitacora buscar(Bitacora bitacora){
         ArrayList<Bitacora> bitacoras = new ArrayList();
         Bitacora nuevabitacora=null;
        
        try{
            
            this.datos = this.sentencias.executeQuery("select * from bitacora where placa="+bitacora.getPlaca());
            
            
                        
            while(datos.next()){
              
               bitacoras.add( new Bitacora(datos.getString(2),datos.getString(3),datos.getString(4),datos.getDate(5),datos.getTime(6),datos.getInt(7),datos.getDate(8),datos.getTime(9),datos.getInt(10)) );
               
               //carga la ultima bitacora
               nuevabitacora = bitacoras.get(bitacoras.size()-1);
               
               
            }
            
            return nuevabitacora;
          
            
            
        }catch (SQLException ex) {
                System.out.println("Error al buscar");
            }
            return nuevabitacora;
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
              
            this.sentencias.executeUpdate("UPDATE bitacora SET fechallegada=CURDATE(),horallegada=CURTIME(),kfinal='"+bitacora.getKfinal()+"' WHERE placa ='"+bitacora.getPlaca()+"' AND kfinal IS NULL ;");
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
              
                    bitacoras.add(new Bitacora(datos.getString(2),datos.getString(3),datos.getString(4),datos.getDate(5),datos.getTime(6),datos.getInt(7),datos.getDate(8),datos.getTime(9),datos.getInt(10)));
               
                }
                return bitacoras;
            } catch (SQLException ex) {
                System.out.println("Error al listar");
            }
            return null; 
    }
    
        
        
        
        
    public boolean ValidarPK(Bitacora bitacora){
        Bitacora bitacora2;
        
        if( buscar(bitacora)!=null)
          {
               bitacora2=buscar(bitacora);
               if(bitacora2.getKfinal()== 0 && bitacora2.getHorallegada()== null && bitacora2.getFechallegada()== null){
                    
                    
                    System.out.println("se encontro una bitacora con valores nulos");
                    return false;
                }
          
          
          }

        return true;
    } 
        
        
    public boolean ValidarFK(Bitacora bitacora){
       
        try 
        {
            this.datos = this.sentencias.executeQuery("select * from vehiculos where placa="+bitacora.getPlaca());
                
            if (datos.next()) 
            {
            
                return true;
              
            }
                
        } catch (SQLException ex){
            System.out.println("Error al validarFK");
            
        }
         
        return false;
       
    }      
        
       
    
}
