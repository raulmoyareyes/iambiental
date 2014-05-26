package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicente Contiene todo lo relacionado con la conectividad con la BD
 * para el Receptor del servidor web: - Conexion - Operaciones CRUD
 */
public class ReceptorDAO {

    private static Connection cnx;

    /**
     * Abre la conexion con la BD
     *
     * @return Devuelve la conexion
     */
    public static Connection openConexion() {
        cnx = null;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/iAmbiental?"
                    + "user=root&password=toor");

        } catch (SQLException ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return cnx;
    }

    /**
     * Cierra la conexion abierta previamente
     */
    public static void closeConexion() {
        try {
            cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Con este método insertaremos los datos recibidos por el sensor en la BD
     *
     * @param id
     * @param dato
     * @param estado
     * @param fecha
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean actualizaDatosSensor(int id, float dato, int estado, Date fecha) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo
                String qry = "UPDATE sensores set dato=?,estado=?,fecha=? WHERE idFisico=?;";

                try (PreparedStatement stmn = cnx.prepareStatement(qry)) {
                    // Atributos de sensor y la fecha
                    stmn.setFloat(1, dato);
                    stmn.setInt(2, estado);
                    stmn.setDate(3, new java.sql.Date(fecha.getTime()));
                    stmn.setInt(4, id);

                    if (stmn.executeUpdate() > 0) {
                        salida = true;
                    }
                }
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return salida;
    }

    /**
     * Recupera los atributos despues de una lectura en BD
     *
     * @param rs Se le pasa el resultado de la consulta
     * @return Devuelve un objeto de tipo Sensor con la información
     */
    public static Sensor recuperaDatosSensor(ResultSet rs) {
        Sensor s = new Sensor();

        try {
            int id = Integer.parseInt(rs.getString("idFisico"));
            float dato = Float.parseFloat(rs.getString("dato"));
            int estado = Integer.parseInt(rs.getString("estado"));
            Date fecha = null; //rs.getString("fecha"); ///////////////////////////////////////////////

            s = new Sensor(id, dato, estado, fecha);
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }

    /**
     * Recuperamos una lectura del sensor
     *
     * @param id_sensor
     * @return
     */
    public static Sensor lecturaSensorBD(int id_sensor) {
        Sensor s = null;
        if (openConexion() != null) {
            try {
                String qry = "SELECT * FROM sensores WHERE idFisico=?";
                try (PreparedStatement stmn = cnx.prepareStatement(qry)) {
                    stmn.setInt(1, id_sensor);
                    try (ResultSet rs = stmn.executeQuery()) {
                        rs.next();
                        s = recuperaDatosSensor(rs);
                    }
                }
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return s;
    }

    /**
     * Con este método insertaremos los datos recibidos por el actuador en la BD
     *
     * @param id
     * @param dato
     * @param estado
     * @param fecha
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean actualizaDatosActuador(int id, float dato, int estado, Date fecha) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo
                String qry = "UPDATE actuadores set dato=?, estado=?, fecha=? WHERE idFisico=?";

                try (PreparedStatement stmn = cnx.prepareStatement(qry)) {
                    // Atributos de actuador y la fecha
                    stmn.setFloat(1, dato);
                    stmn.setInt(2, estado);
                    stmn.setDate(3, new java.sql.Date(fecha.getTime()));
                    stmn.setInt(4, id);

                    if (stmn.executeUpdate() > 0) {
                        salida = true;
                    }
                }
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return salida;
    }

    /**
     * Recupera los atributos despues de una lectura en BD
     *
     * @param rs Se le pasa el resultado de la consulta
     * @return Devuelve un objeto de tipo Sensor con la información
     */
    public static Actuador recuperaDatosActuador(ResultSet rs) {
        Actuador a = new Actuador();

        try {
            int id = Integer.parseInt(rs.getString("idFisico"));
            float dato = Float.parseFloat(rs.getString("dato"));
            int estado = Integer.parseInt(rs.getString("estado"));
            Date fecha = null; //rs.getString("fecha"); ////////////////////////////////////////////////
            String ip = rs.getString("ip");
            String puerto = rs.getString("puerto");

            a = new Actuador(id, dato, estado, fecha, ip, puerto);
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return a;
    }

    /**
     * Recuperamos una lectura del sensor
     *
     * @param id_actuador
     * @return
     */
    public static Actuador lecturaActuadorBD(int id_actuador) {
        Actuador a = null;
        if (openConexion() != null) {
            try {
                String qry = "SELECT * FROM actuadores WHERE idFisico=?";
                try (PreparedStatement stmn = cnx.prepareStatement(qry)) {
                    stmn.setInt(1, id_actuador);
                    try (ResultSet rs = stmn.executeQuery()) {
                        rs.next();
                        a = recuperaDatosActuador(rs);
                    }
                }
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return a;
    }
}
