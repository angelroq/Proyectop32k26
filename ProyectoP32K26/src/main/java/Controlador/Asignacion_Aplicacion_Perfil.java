/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Jorge Reyes
 */
public class Asignacion_Aplicacion_Perfil {
    public Asignacion_Aplicacion_Perfil(int AplCódigo, int PerCódigo) {
        this.AplCódigo = AplCódigo;
        this.PerCódigo = PerCódigo;
    }

    public int getAplCódigo() {
        return AplCódigo;
    }

    public void setAplCódigo(int AplCódigo) {
        this.AplCódigo = AplCódigo;
    }

    public int getPerCódigo() {
        return PerCódigo;
    }

    public void setPerCódigo(int PerCódigo) {
        this.PerCódigo = PerCódigo;
    }

    @Override
    public String toString() {
        return "Asignacion_Aplicacion_Perfil{" + "AplCódigo=" + AplCódigo + ", PerCódigo=" + PerCódigo + '}';
    }


    int AplCódigo, PerCódigo;
    
    
    public Asignacion_Aplicacion_Perfil(){
    
}
    
}
