/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Marco Hernandez marzo 2026.
package Controlador; // Define el paquete al que pertenece la clase

/**
 * Clase que representa la entidad Usuario en el sistema.
 * @author deleo
 */
public class Usuario {

    // Atributos privados que representan los datos del usuario
    private int usucodigo;        // Identificador único del usuario
    private String usunombre;      // Nombre completo del usuario
    private String usucorreo;      // Dirección de correo electrónico
    private String usutelefono;    // Número de contacto
    private String usudireccion;   // Dirección física del usuario
    private String usucontrasena;  // Clave de acceso al sistema
    private String usuestado;      // Estado de la cuenta (ej. Activo/Inactivo)

    // Constructor vacío (necesario para frameworks o inicializaciones sin datos)
    public Usuario() {
    }

    // Constructor con parámetros para inicializar todos los atributos al crear el objeto
    public Usuario(int codigo, String nombre, String correo, String telefono, String direccion, String contrasena, String estado) {
        this.usucodigo = codigo;      // Asigna el código recibido al atributo de la clase
        this.usunombre = nombre;      // Asigna el nombre recibido
        this.usucorreo = correo;      // Asigna el correo recibido
        this.usutelefono = telefono;  // Asigna el teléfono recibido
        this.usudireccion = direccion; // Asigna la dirección recibida
        this.usucontrasena = contrasena; // Asigna la contraseña recibida
        this.usuestado = estado;      // Asigna el estado recibido
    }

    // --- MÉTODOS GETTERS Y SETTERS ---
    // Sirven para obtener (get) o modificar (set) los atributos privados

    public int getCodigo() {
        return usucodigo; // Devuelve el valor del código
    }

    public void setCodigo(int codigo) {
        this.usucodigo = codigo; // Actualiza el valor del código
    }

    public String getNombre() {
        return usunombre; // Devuelve el nombre
    }

    public void setNombre(String nombre) {
        this.usunombre = nombre; // Actualiza el nombre
    }
    
    public String getCorreo() {
        return usucorreo; // Devuelve el correo
    }

    public void setCorreo(String correo) {
        this.usucorreo = correo; // Actualiza el correo
    }

    public String getTelefono() {
        return usutelefono; // Devuelve el teléfono
    }

    public void setTelefono(String telefono) {
        this.usutelefono = telefono; // Actualiza el teléfono
    }

    public String getDireccion() {
        return usudireccion; // Devuelve la dirección
    }

    public void setDireccion(String direccion) {
        this.usudireccion = direccion; // Actualiza la dirección
    }

    public String getContrasena() {
        return usucontrasena; // Devuelve la contraseña
    }

    public void setContrasena(String contrasena) {
        this.usucontrasena = contrasena; // Actualiza la contraseña
    }

    public String getEstado() {
        return usuestado; // Devuelve el estado actual
    }

    public void setEstado(String estado) {
        this.usuestado = estado; // Actualiza el estado (ej. Activo/Inactivo)
    }
}

