/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Danny_PC
 */
public class Vehiculo {
    private String placa;
    private String descripcion;

    public Vehiculo() {
        this.placa = null;
        this.descripcion = null;
    }

    public Vehiculo(String placa, String descripcion) {
        this.placa = placa;
        this.descripcion = descripcion;
    }

    public Vehiculo(String placa) {
        this.placa = placa;
      
    }
    
    public boolean Comprobar() {
        //retorna true si no estan vacios
        return  this.descripcion!=null & this.placa!=null;
        
    }

    
    
    
    

    //metodos de acceso
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
    
    
    
    
}
