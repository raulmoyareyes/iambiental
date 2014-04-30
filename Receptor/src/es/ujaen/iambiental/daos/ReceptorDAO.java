/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Vicente
 * Contiene todo lo relacionado con la conectividad con la BD
 * para el Receptor del servidor web:
 *  - Conexion
 *  - Operaciones CRUD
 */
public class ReceptorDAO {
    private static Connection cnx;
    private static String coonPoolName = ":mysql://localhost:3306/iambiental";
    
    /**
     * Abre la conexion con la BD
     * @return Devuelve la conexion
     */
    public static Connection openConexion() {
        cnx = null;
        try {

            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("jdbc" + coonPoolName);
            cnx = ds.getConnection();

        } catch (Exception ex) {
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
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean insertaDatosSensor(int id, float dato, String descripcion,
            int estado, Timestamp fecha, String ip, String puerto, int dependencia_id) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id
                String qry = "INSERT INTO sensores(id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmn = cnx.prepareStatement(qry);

                // Atributos de sensor y el time_stamp en milisegundos
                stmn.setInt(1, id);
                stmn.setFloat(2, dato);
                stmn.setString(3, descripcion);
                stmn.setInt(4, estado);
                stmn.setTimestamp(5, fecha);
                stmn.setString(6, ip);
                stmn.setString(7, puerto);
                stmn.setInt(8, dependencia_id);

                if (stmn.executeUpdate() > 0) {
                    salida = true;
                }

                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return salida;
    }
    
    /**
     * Recupera los atributos despues de una lectura en BD
     * @param rs Se le pasa el resultado de la consulta
     * @return Devuelve un objeto de tipo Sensor con la información
     */
    
    public static Sensor recuperaDatosSensor(ResultSet rs) {
        Sensor s = new Sensor();
        
        try {
            int id = Integer.parseInt(rs.getString("id"));
            float dato = Float.parseFloat(rs.getString("dato"));
            String descripcion = rs.getString("descripcion");
            int estado = Integer.parseInt(rs.getString("estado"));
            Timestamp time_stamp = Timestamp.valueOf(rs.getString("fecha"));
            String ip = rs.getString("ip");
            String puerto = rs.getString("puerto");
            int dependencia_id = Integer.parseInt(rs.getString("dependencia_id"));

            s = new Sensor(id, dato, descripcion, estado, time_stamp, ip, puerto, dependencia_id);
        } catch (Exception ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }
    
    /**
     * Recuperamos una lectura del sensor
     * @param id_sensor
     * @return 
     */
    public static Sensor lecturaSensorBD (int id_sensor) {
        Sensor s = null;
        if (openConexion() != null) {
            try {
                String qry = "SELECT * FROM sensores WHERE id=?";
                PreparedStatement stmn = cnx.prepareStatement(qry);
                stmn.setInt(1, id_sensor);
                ResultSet rs = stmn.executeQuery();
                rs.next();
                s = recuperaDatosSensor(rs);
                rs.close();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return s;
    }
    
    /**
     * Con este método insertaremos los datos recibidos por el actuador en la BD
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean insertaDatosActuador(int id, float dato,  int dependencia, String descripcion,
            int estado, Timestamp fecha, String ip, String puerto) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id
                String qry = "INSERT INTO actuadores(id, dato, descripcion, estado, fecha, ip, puerto, dependencia) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmn = cnx.prepareStatement(qry);

                // Atributos de actuador y el time_stamp en milisegundos
                stmn.setInt(1, id);
                stmn.setFloat(2, dato);
                stmn.setInt(3, dependencia);
                stmn.setString(4, descripcion);
                stmn.setInt(5, estado);
                stmn.setTimestamp(6, fecha);
                stmn.setString(7, ip);
                stmn.setString(8, puerto);

                if (stmn.executeUpdate() > 0) {
                    salida = true;
                }

                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return salida;
    }
    
    /**
     * Recupera los atributos despues de una lectura en BD
     * @param rs Se le pasa el resultado de la consulta
     * @return Devuelve un objeto de tipo Sensor con la información
     */
    
    public static Actuador recuperaDatosActuador(ResultSet rs) {
        Actuador a = new Actuador();
        
        try {
            int id = Integer.parseInt(rs.getString("id"));
            float dato = Float.parseFloat(rs.getString("dato"));
            String descripcion = rs.getString("descripcion");
            int estado = Integer.parseInt(rs.getString("estado"));
            Timestamp time_stamp = Timestamp.valueOf(rs.getString("fecha"));
            String ip = rs.getString("ip");
            String puerto = rs.getString("puerto");
            int dependencia_id = Integer.parseInt(rs.getString("dependencia"));

            a = new Actuador(id, dato, descripcion, estado, time_stamp, ip, puerto, dependencia_id);
        } catch (Exception ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return a;
    }
    
    /**
     * Recuperamos una lectura del sensor
     * @param id_actuador
     * @return 
     */
    public static Actuador lecturaActuadorBD (int id_actuador) {
        Actuador a = null;
        if (openConexion() != null) {
            try {
                String qry = "SELECT * FROM actuadores WHERE id=?";
                PreparedStatement stmn = cnx.prepareStatement(qry);
                stmn.setInt(1, id_actuador);
                ResultSet rs = stmn.executeQuery();
                rs.next();
                a = recuperaDatosActuador(rs);
                rs.close();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return a;
    }
}

