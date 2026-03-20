//Documentación: Astrid Fernanda Ruíz López 9959 24 2976
package Modelo;

import Controlador.clsBitacora;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; //manejo de fechas usado por la bd sql
import java.text.SimpleDateFormat;
import java.time.LocalDateTime; //para la fechas y horas en java
import java.util.ArrayList;
import java.util.List;

public class BitacoraDAO {

    private static final String SQL_SELECT = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora";
    private static final String SQL_INSERT = "INSERT INTO Bitacora(Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion) VALUES(?, ?, ?, ?, ?, ?)";
    //Se agregaron varios querys para poder buscar por diferentes tipos en la bitacora (ya sea codigo, usuario, fecha,etc) 
    private static final String SQL_QUERY_POR_CODIGO = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora WHERE Bitcodigo=?";
    private static final String SQL_QUERY_POR_USUARIO = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora WHERE Usucodigo=?";
    private static final String SQL_QUERY_POR_APLICACION = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora WHERE Aplcodigo=?";
    private static final String SQL_QUERY_POR_FECHAS = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora WHERE Bitfecha BETWEEN ? AND ?"; //between y and para buscar desde el inicio d fecha que el usuario seleccione hasta el final (el intervalo d fechas)
    private static final String SQL_QUERY_POR_ACCION = "SELECT Bitcodigo, Usucodigo, Aplcodigo, Bitfecha, Bitip, Bitequipo, Bitaccion FROM Bitacora WHERE Bitaccion=?";


    // SELECT (trae todos los registros) 
        public String fechaActual() {

        java.util.Date fecha = new java.util.Date();
        //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

        return formatoFecha.format(fecha);

    }

    public static String horaActual() {

        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("hh:mm:ss");

        return formatoFecha.format(fecha);

    }
    
    private String obtenerNombrePc() throws UnknownHostException {
        // return System.getProperty("user.name");        
        return InetAddress.getLocalHost().getHostName();
    }
            
    private String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }        
    public List<clsBitacora> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsBitacora bitacora = null;
        List<clsBitacora> bitacoras = new ArrayList<clsBitacora>(); //lista para almacenar todos los registros

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                //valores de cada columna del ResultSet
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int Usucodigo    = rs.getInt("Usucodigo");
                int Aplcodigo    = rs.getInt("Aplcodigo");
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(Usucodigo);
                bitacora.setAplcodigo(Aplcodigo);
                
                //conversión de Timestamp a LocalDateTime
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion);

                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacoras;
    }

    // Isert
    public int insert(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String ipAsignada;
        String nombrepcAsignada;
        ipAsignada = " ";
        nombrepcAsignada = " ";        
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            try {
                   ipAsignada= obtenerIP();
                   nombrepcAsignada= obtenerNombrePc();            
            } catch (UnknownHostException ex)
                {
                }                           
            //asignación d valores a los parametros
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, bitacora.getUsucodigo());
            stmt.setInt(2, bitacora.getAplcodigo());
            stmt.setTimestamp(3, Timestamp.valueOf(fechaActual())); //se convierte la fecha a Timestamp, ese timestamp se envía a la base d datos
            stmt.setString(4, ipAsignada);
            stmt.setString(5, nombrepcAsignada);
            stmt.setString(6, bitacora.getBitaccion());

            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    // Query por código 
    public clsBitacora queryPorCodigo(clsBitacora bitacora) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY_POR_CODIGO);
            stmt = conn.prepareStatement(SQL_QUERY_POR_CODIGO);
            // se pasa el código que se desea buscar
            stmt.setInt(1, bitacora.getBitcodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int Usucodigo    = rs.getInt("Usucodigo");
                int Aplcodigo    = rs.getInt("Aplcodigo");
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(Usucodigo);
                bitacora.setAplcodigo(Aplcodigo);
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacora;
    }

    //Se hace el mismo proceso para los demás querys: ejecutar la consulta, recorrer el ResultSet, convertir cada registro en un objeto Bitacora y agregarlo a una lista.
    
    // Query por usuario
    public List<clsBitacora> queryPorUsuario(int Usucodigo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsBitacora bitacora = null;
        List<clsBitacora> bitacoras = new ArrayList<clsBitacora>();

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY_POR_USUARIO);
            stmt = conn.prepareStatement(SQL_QUERY_POR_USUARIO);
            stmt.setInt(1, Usucodigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int usu          = rs.getInt("Usucodigo");
                int Aplcodigo    = rs.getInt("Aplcodigo");
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(usu);
                bitacora.setAplcodigo(Aplcodigo);
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion);

                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacoras;
    }

    // Query por aplicación
    public List<clsBitacora> queryPorAplicacion(int Aplcodigo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsBitacora bitacora = null;
        List<clsBitacora> bitacoras = new ArrayList<clsBitacora>();

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY_POR_APLICACION);
            stmt = conn.prepareStatement(SQL_QUERY_POR_APLICACION);
            stmt.setInt(1, Aplcodigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int Usucodigo    = rs.getInt("Usucodigo");
                int apl          = rs.getInt("Aplcodigo");
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(Usucodigo);
                bitacora.setAplcodigo(apl);
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion);

                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacoras;
    }

    // Query por rango de fechas
    public List<clsBitacora> queryPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsBitacora bitacora = null;
        List<clsBitacora> bitacoras = new ArrayList<clsBitacora>();

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY_POR_FECHAS);
            stmt = conn.prepareStatement(SQL_QUERY_POR_FECHAS);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int Usucodigo    = rs.getInt("Usucodigo");
                int Aplcodigo    = rs.getInt("Aplcodigo");
                //Se obtiene la fecha desde la base de datos en formato sql
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(Usucodigo);
                bitacora.setAplcodigo(Aplcodigo);
                //Se convierte el timestamp al formato de java (localtime) para poder manejar la fecha aquí 
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion);

                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacoras;
    }

    // Query por acción
    public List<clsBitacora> queryPorAccion(String Bitaccion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsBitacora bitacora = null;
        List<clsBitacora> bitacoras = new ArrayList<clsBitacora>();

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY_POR_ACCION);
            stmt = conn.prepareStatement(SQL_QUERY_POR_ACCION);
            stmt.setString(1, Bitaccion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Bitcodigo    = rs.getInt("Bitcodigo");
                int Usucodigo    = rs.getInt("Usucodigo");
                int Aplcodigo    = rs.getInt("Aplcodigo");
                Timestamp ts     = rs.getTimestamp("Bitfecha");
                String Bitip     = rs.getString("Bitip");
                String Bitequipo = rs.getString("Bitequipo");
                String Bitaccion2 = rs.getString("Bitaccion");

                bitacora = new clsBitacora();
                bitacora.setBitcodigo(Bitcodigo);
                bitacora.setUsucodigo(Usucodigo);
                bitacora.setAplcodigo(Aplcodigo);
                if (ts != null) bitacora.setBitfecha(ts.toLocalDateTime());
                bitacora.setBitip(Bitip);
                bitacora.setBitequipo(Bitequipo);
                bitacora.setBitaccion(Bitaccion2);

                bitacoras.add(bitacora);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return bitacoras;
    }
}