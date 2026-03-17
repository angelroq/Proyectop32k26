/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;//Maria Celeste Mayen Ibarra 9959-23-3755

import Controlador.AsignacionPerfilUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionPerfilUsuarioDAO {

    //modifique esto para que me cuadrara por que siempre lo escribo mal jejej
    private static final String SQL_SELECT = "SELECT Usucodigo, Percodigo FROM asignacionperfilusuario";
    private static final String SQL_INSERT = "INSERT INTO asignacionperfilusuario(Usucodigo, Percodigo) VALUES(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM asignacionperfilusuario WHERE Usucodigo=? AND Percodigo=?";

    public List<AsignacionPerfilUsuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AsignacionPerfilUsuario> lista = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AsignacionPerfilUsuario asig = new AsignacionPerfilUsuario();
                //aqui se guarda como en una cajita el objeto
                asig.setUsucodigo(rs.getInt("Usucodigo"));
                asig.setPercodigo(rs.getInt("Percodigo"));
                lista.add(asig);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lista;
    }

    public int insert(AsignacionPerfilUsuario asig) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();//para poder insertarloos
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, asig.getUsucodigo());
            stmt.setInt(2, asig.getPercodigo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}