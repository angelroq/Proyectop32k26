/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Controlador.Asignacion_Aplicacion_Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Xander reyes
 */
public class Asignacion_Aplicacion_PerfilDAO {
    
    // Sentencia SQL para obtener todos los registros de la tabla Asignacion_Aplicacion_Perfil
private static final String SQL_SELECT = 
"SELECT AplCódigo, PerCódigo FROM Asignacion_Aplicacion_Perfil";

// Sentencia SQL para insertar un nuevo registro en la tabla Asignacion_Aplicacion_Perfil
private static final String SQL_INSERT = 
"INSERT INTO Asignacion_Aplicacion_Perfil(AplCódigo, PerCódigo) VALUES(?,?)";

// Sentencia SQL para actualizar el PerCódigo de un registro según su AplCódigo
private static final String SQL_UPDATE = 
"UPDATE Asignacion_Aplicacion_Perfil SET PerCódigo=? WHERE AplCódigo=?";

// Sentencia SQL para eliminar un registro según su AplCódigo
private static final String SQL_DELETE = 
"DELETE FROM Asignacion_Aplicacion_Perfil WHERE AplCódigo=?";

// Sentencia SQL para consultar un registro específico según su AplCódigo
private static final String SQL_QUERY = 
"SELECT AplCódigo, PerCódigo FROM Asignacion_Aplicacion_Perfil WHERE AplCódigo=?";


// Método que obtiene todos los registros de la tabla Asignacion_Aplicacion_Perfil
public List<Asignacion_Aplicacion_Perfil> select() {

    // Variables para la conexión y ejecución de la consulta
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Objeto que representará cada registro de la tabla
    Asignacion_Aplicacion_Perfil asignacion = null;

    // Lista que almacenará todos los registros encontrados
    List<Asignacion_Aplicacion_Perfil> asignaciones = new ArrayList<>();

    try {

        // Se obtiene la conexión a la base de datos
        conn = Conexion.getConnection();

        // Se prepara la consulta SQL
        stmt = conn.prepareStatement(SQL_SELECT);

        // Se ejecuta la consulta
        rs = stmt.executeQuery();

        // Se recorren todos los registros obtenidos
        while (rs.next()) {

            // Se obtienen los valores de cada columna
            int AplCódigo = rs.getInt("AplCódigo");
            int PerCódigo = rs.getInt("PerCódigo");

            // Se crea el objeto asignación y se asignan los valores
            asignacion = new Asignacion_Aplicacion_Perfil();
            asignacion.setAplCódigo(AplCódigo);
            asignacion.setPerCódigo(PerCódigo);

            // Se agrega el objeto a la lista
            asignaciones.add(asignacion);
        }

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {

        // Se cierran los recursos utilizados
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    // Se devuelve la lista de registros
    return asignaciones;
}


// Método para insertar un nuevo registro en la tabla Asignacion_Aplicacion_Perfil
public int insert(Asignacion_Aplicacion_Perfil asignacion) {

    Connection conn = null;
    PreparedStatement stmt = null;

    // Variable que indica cuántos registros fueron afectados
    int rows = 0;

    try {

        // Se obtiene la conexión
        conn = Conexion.getConnection();

        // Se prepara la consulta SQL de inserción
        stmt = conn.prepareStatement(SQL_INSERT);

        // Se asignan los valores a los parámetros de la consulta
        stmt.setInt(1, asignacion.getAplCódigo());
        stmt.setInt(2, asignacion.getPerCódigo());

        // Se muestra la consulta en consola
        System.out.println("Ejecutando query:" + SQL_INSERT);

        // Se ejecuta la inserción
        rows = stmt.executeUpdate();

        // Se muestran los registros afectados
        System.out.println("Registros afectados:" + rows);

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {

        // Se cierran los recursos utilizados
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}


// Método para actualizar un registro de la tabla Asignacion_Aplicacion_Perfil
public int update(Asignacion_Aplicacion_Perfil asignacion) {

    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try {

        // Se obtiene la conexión a la base de datos
        conn = Conexion.getConnection();

        System.out.println("Ejecutando query: " + SQL_UPDATE);

        // Se prepara la consulta de actualización
        stmt = conn.prepareStatement(SQL_UPDATE);

        // Se asignan los nuevos valores
        stmt.setInt(1, asignacion.getPerCódigo());

        // Se especifica el AplCódigo del registro a actualizar
        stmt.setInt(2, asignacion.getAplCódigo());

        // Se ejecuta la actualización
        rows = stmt.executeUpdate();

        System.out.println("Registros actualizados:" + rows);

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {

        // Se cierran los recursos utilizados
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}


// Método para eliminar un registro según su AplCódigo
public int delete(Asignacion_Aplicacion_Perfil asignacion) {

    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try {

        // Se obtiene la conexión a la base de datos
        conn = Conexion.getConnection();

        System.out.println("Ejecutando query:" + SQL_DELETE);

        // Se prepara la consulta de eliminación
        stmt = conn.prepareStatement(SQL_DELETE);

        // Se asigna el AplCódigo del registro a eliminar
        stmt.setInt(1, asignacion.getAplCódigo());

        // Se ejecuta la eliminación
        rows = stmt.executeUpdate();

        System.out.println("Registros eliminados:" + rows);

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {

        // Se cierran los recursos utilizados
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}


// Método que busca un registro específico según su AplCódigo
public Asignacion_Aplicacion_Perfil query(Asignacion_Aplicacion_Perfil asignacion) {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {

        // Se obtiene la conexión a la base de datos
        conn = Conexion.getConnection();

        System.out.println("Ejecutando query:" + SQL_QUERY);

        // Se prepara la consulta
        stmt = conn.prepareStatement(SQL_QUERY);

        // Se establece el AplCódigo a buscar
        stmt.setInt(1, asignacion.getAplCódigo());

        // Se ejecuta la consulta
        rs = stmt.executeQuery();

        // Si se encuentra el registro
        while (rs.next()) {

            int AplCódigo = rs.getInt("AplCódigo");
            int PerCódigo = rs.getInt("PerCódigo");

            // Se crea el objeto con los datos encontrados
            asignacion = new Asignacion_Aplicacion_Perfil();
            asignacion.setAplCódigo(AplCódigo);
            asignacion.setPerCódigo(PerCódigo);
        }

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {

        // Se cierran los recursos utilizados
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    // Se devuelve el registro encontrado
    return asignacion;
}
   
}
