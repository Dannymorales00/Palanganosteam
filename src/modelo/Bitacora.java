/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author mata6
 */
public class Bitacora {

    private int id;
    private String placa;
    private String provincia;
    private String destino;
    private Date fechasalida;
    private Time horasalida;
    private int kinicial;
    private Date fechallegada;
    private Time horallegada;
    private int kfinal;

    public Bitacora() {

    }

    public String getPlaca() {
        return placa;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Time getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(Time horasalida) {
        this.horasalida = horasalida;
    }

    public int getKinicial() {
        return kinicial;
    }

    public void setKinicial(int kinicial) {
        this.kinicial = kinicial;
    }

    public Date getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(Date fechallegada) {
        this.fechallegada = fechallegada;
    }

    public Time getHorallegada() {
        return horallegada;
    }

    public void setHorallegada(Time horallegada) {
        this.horallegada = horallegada;
    }

    public int getKfinal() {
        return kfinal;
    }

    public void setKfinal(int kfinal) {
        this.kfinal = kfinal;
    }

}
