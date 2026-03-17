//Anthony Hetzael marzo 2026
package Modelo;

import Modelo.Conexion;
import Controlador.Aplicaciones;

import java.sql.*;

public class AplicacionesDAO { //DAO (Direct Access Object) acceso directo a datos 
//cada clase debe tener un DAO

    //INSERT que guarada una nueva aplicacion en la base de datos
    //sentencias SQL para insertar datos en la tabla Aplicaciones
    public void insert(Aplicaciones app) {

        String sql = "INSERT INTO Aplicaciones(APLNOMBRE, APLESTADO) VALUES (?,?)"; 

        try (Connection conn = Conexion.getConnection(); //se obtiene la conexion a la BD
             PreparedStatement ps = conn.prepareStatement(sql)) { //Se prepara la consulta

            ps.setString(1, app.getAplNombre()); //// Asignar valores a los parámetros del SQL
            ps.setString(2, app.getAplEstado());

            ps.executeUpdate(); //ejecución del insert

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //UPDATE: busca la aplicacion de la tabla y actualiza sus datos
    public void update(Aplicaciones app) {

        String sql = "UPDATE Aplicaciones SET APLNOMBRE=?, APLESTADO=? WHERE APLCODIGO=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, app.getAplNombre());
            ps.setString(2, app.getAplEstado());
            ps.setInt(3, app.getAplCodigo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //DELETE: parra borrar una aplicacion de la tabla
    public void delete(int codigo) {

        String sql = "DELETE FROM Aplicaciones WHERE APLCODIGO=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //QUERY POR ID: Busca una aplicación según su código
    public Aplicaciones query(int codigo) {

        Aplicaciones app = null; //Variable donde se guardará el resultado

        String sql = "SELECT * FROM Aplicaciones WHERE APLCODIGO=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //Se asigna el código al parámetro de la consulta
            ps.setInt(1, codigo);

            ResultSet rs = ps.executeQuery(); //ejecuta la consulta

            if (rs.next()) { //si existe un registro

                app = new Aplicaciones();
                //se obtienen datos del ResultSet
                app.setAplCodigo(rs.getInt("APLCODIGO"));
                app.setAplNombre(rs.getString("APLNOMBRE"));
                app.setAplEstado(rs.getString("APLESTADO"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return app; //retorna el objeto encontrado o null

    }

}