/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author 50240
 */
import java.sql.Timestamp;

public class AsignacionPerfilUsuario{
    private int USU_CODIGO;
    private int PER_CODIGO;
    private boolean APU_ESTADO;
    private Timestamp APU_FECHA_ASIG;

  
    public AsignacionPerfilUsuario() {}

    public AsignacionPerfilUsuario(int USU_CODIGO, int PER_CODIGO, boolean APU_ESTADO) {
        this.USU_CODIGO = USU_CODIGO;
        this.PER_CODIGO = PER_CODIGO;
        this.APU_ESTADO = APU_ESTADO;
    }

    
    public int getUSU_CODIGO() {
        return USU_CODIGO; 
    }
    
     public void setUSU_CODIGO(int USU_CODIGO) {
        this.USU_CODIGO = USU_CODIGO;
    }

    public int getPER_CODIGO() {
        return PER_CODIGO;
    }

    public void setPER_CODIGO(int PER_CODIGO) {
        this.PER_CODIGO = PER_CODIGO;
    }

    public boolean isAPU_ESTADO() {
        return APU_ESTADO;
    }

    public void setAPU_ESTADO(boolean APU_ESTADO) {
        this.APU_ESTADO = APU_ESTADO;
    }

    public Timestamp getAPU_FECHA_ASIG() {
        return APU_FECHA_ASIG;
    }

    public void setAPU_FECHA_ASIG(Timestamp APU_FECHA_ASIG) {
        this.APU_FECHA_ASIG = APU_FECHA_ASIG;
    }

    

}