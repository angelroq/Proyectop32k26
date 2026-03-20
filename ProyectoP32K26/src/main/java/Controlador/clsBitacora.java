/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.time.LocalDateTime;

public class clsBitacora {
    private int Bitcodigo;
    private int Usucodigo;
    private int Aplcodigo;
    private LocalDateTime Bitfecha;
    private String Bitip;
    private String Bitequipo;
    private String Bitaccion;

    // Constructor vacío
    public clsBitacora() {
    }

    // Constructor con parámetros
    public clsBitacora(int Usucodigo, int Aplcodigo, LocalDateTime Bitfecha,String Bitip, String Bitequipo, String Bitaccion) {
        this.Usucodigo = Usucodigo;
        this.Aplcodigo = Aplcodigo;
        this.Bitfecha  = Bitfecha;
        this.Bitip     = Bitip;
        this.Bitequipo = Bitequipo;
        this.Bitaccion = Bitaccion;
    }

    // Getters y Setters
    public int getBitcodigo() { return Bitcodigo; }
    public void setBitcodigo(int Bitcodigo) { this.Bitcodigo = Bitcodigo; }

    public int getUsucodigo() { return Usucodigo; }
    public void setUsucodigo(int Usucodigo) { this.Usucodigo = Usucodigo; }

    public int getAplcodigo() { return Aplcodigo; }
    public void setAplcodigo(int Aplcodigo) { this.Aplcodigo = Aplcodigo; }

    public LocalDateTime getBitfecha() { return Bitfecha; }
    public void setBitfecha(LocalDateTime Bitfecha) { this.Bitfecha = Bitfecha; }

    public String getBitip() { return Bitip; }
    public void setBitip(String Bitip) { this.Bitip = Bitip; }

    public String getBitequipo() { return Bitequipo; }
    public void setBitequipo(String Bitequipo) { this.Bitequipo = Bitequipo; }

    public String getBitaccion() { return Bitaccion; }
    public void setBitaccion(String Bitaccion) { this.Bitaccion = Bitaccion; }

    @Override
    public String toString() {
        return "Bitacora{" + "Bitcodigo=" + Bitcodigo + ", Usucodigo=" + Usucodigo + ", Aplcodigo=" + Aplcodigo + ", Bitfecha=" + Bitfecha + ", Bitip='" + Bitip + ", Bitequipo='" + Bitequipo + ", Bitaccion='" + Bitaccion + '}';
    }

}
