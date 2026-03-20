/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author visitante
 */
public class clsUsuarioConectado {
    private static int UsuCodigo;
    private static String UsuNombre;     

    public clsUsuarioConectado() {
    }

    public static int getUsuCodigo() {
        return UsuCodigo;
    }

    public static void setUsuCodigo(int UsuCodigo) {
        clsUsuarioConectado.UsuCodigo = UsuCodigo;
    }

    public static String getUsuNombre() {
        return UsuNombre;
    }

    public static void setUsuNombre(String UsuNombre) {
        clsUsuarioConectado.UsuNombre = UsuNombre;
    }
    

}
