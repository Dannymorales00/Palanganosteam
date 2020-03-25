/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import config.Manejoconfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.FrmConfiguracion;


/**
 *
 * @author Danny_PC
 */
public class Conexion {
        Manejoconfig manejoconfig = new Manejoconfig();
    
        //profe

    private Statement sentencias;
    private ResultSet datos;
    
    
    
    
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";

    
    
    public void Conectar() {
       
        
        String datos[]=manejoconfig.leer();
        
        if(datos!=null){
            System.out.println("no es nulo el vector");
            //motor-basededatos-servidor-usuario-contraseña
            
            
            
            String user = datos[3] ;
            String password =datos[4];
            String url = "jdbc:"+datos[0]+"://"+datos[2]+"/"+datos[1]+"?useServerPrepStmts=true";
            conn = null; 
       
        
        
            try {
            
                conn = DriverManager.getConnection(url, user, password);
                this.sentencias=this.conn.createStatement();
                if(conn!=null)
                {
                    System.out.println("se conecto la madre");
                    
                }       
            
             
            } catch (SQLException e) {
            
                System.out.println("no se conecto la madre");
                System.out.println(e);
                FrmConfiguracion frmconfig= new FrmConfiguracion();
                frmconfig.setVisible(true);
            } 
        
            
        }
        
        
    }
    
    public void Create(String nombre,String ciudad) {
       
        try {
                                                                                                            //sentencia para retornar datos ingresados                                                        
            this.sentencias.executeUpdate("insert into datosgenerales values(null,'"+nombre+"','"+ciudad+"')",Statement.RETURN_GENERATED_KEYS);
            
            //datos es como un array de datos
            //guardamos los datos insertados en la variable datos 
            this.datos = this.sentencias.getGeneratedKeys();
            
            if(datos.next())
            {
                //mostramos los datos insertados
                System.out.println(datos.getInt(1));
            
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al agregar");
        }
        
        
    }
    
    
    public void read(int id) {
        
        
        try {
            // buscamos por id
            this.datos = this.sentencias.executeQuery("select * from datosgenerales where id="+id);
            
            if(datos.next())
            {
                //mostramos todos los datos de un registro consultado por id
                System.out.println(datos.getInt(1));
                System.out.println(datos.getString(2));
                System.out.println(datos.getString(3));
            
            }else {System.out.println("no hay mas registros");}
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void read(String nombre) {
        
        
        try {
            // buscamos por id
            this.datos = this.sentencias.executeQuery("select * from datosgenerales where nombre='"+nombre+"'");
            
            if(datos.next())
            {
                //mostramos todos los datos de un registro consultado por id
                System.out.println(datos.getInt(1));
                System.out.println(datos.getString(2));
                System.out.println(datos.getString(3));
            
            }else {System.out.println("no hay mas registros");}
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void reads(String ciudad) {
        
        
        try {
            // buscamos por id
            this.datos = this.sentencias.executeQuery("select * from datosgenerales where ciudad='"+ciudad+"'");
            
            while(datos.next())
            {
                //mostramos todos los datos de un registro consultado por id
                System.out.println(datos.getInt(1));
                System.out.println(datos.getString(2));
                System.out.println(datos.getString(3));
            
            }
            System.out.println("no hay mas registros");
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void Desconectar() {
        conn=null;
        
        if(conn==null)
        {
            System.out.println("desconectamos la madre");
        
        }
    
      }
    
    
    
    
    public void update(int id,String nombre,String ciudad) {
        
        try {
            this.sentencias.executeUpdate("update datosgenerales set nombre='"+nombre+"',ciudad='"+ciudad+"' where id="+id);
            
        } catch (SQLException ex) {
            System.out.println("Error update");
        }
    }
    
    
    
    public void delete(int id) {
        
        try {
            this.sentencias.executeUpdate("delete from datosgenerales where id="+id);
            
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar");
        }
    
    }
    
    
}
