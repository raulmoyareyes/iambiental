/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Abre la conexion con la BD
     * @return Devuelve la conexion
     */
    public static Connection openConexion() {
        cnx = null;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/iAmbiental?"
            + "user=root&password=123456");

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
    public static boolean actualizaDatosSensor(int id, float dato, int estado, String fecha) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo
                String qry = "UPDATE sensores"
                           + "set dato=?,estado=?,fecha=?"
                           + "WHERE dispositivo_id=?;";
                PreparedStatement stmn = cnx.prepareStatement(qry);

                // Atributos de sensor y la fecha
                stmn.setFloat(1, dato);
                stmn.setInt(2, estado);
                stmn.setString(3, fecha);
                stmn.setInt(4, id);
                

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
            int id = Integer.parseInt(rs.getString("dispositivo_id"));
            float dato = Float.parseFloat(rs.getString("dato"));
            int estado = Integer.parseInt(rs.getString("estado"));
            String fecha = rs.getString("fecha");

            s = new Sensor(id, dato, estado, fecha);
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
                String qry = "SELECT * FROM sensores WHERE dispositivo_id=?";
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
     * @param id
     * @param dato
     * @param estado
     * @param fecha
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean actualizaDatosActuador(int id, float dato, int estado, String fecha) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo
                String qry = "UPDATE actuadores"
                           + "set dato=?,estado=?,fecha=?"
                           + "WHERE dispositivo_id=?;";
                PreparedStatement stmn = cnx.prepareStatement(qry);

                // Atributos de actuador y la fecha
                stmn.setFloat(1, dato);
                stmn.setInt(2, estado);
                stmn.setString(3, fecha);
                stmn.setInt(4, id);
                

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
            int id = Integer.parseInt(rs.getString("dispositivo_id"));
            float dato = Float.parseFloat(rs.getString("dato"));
            int estado = Integer.parseInt(rs.getString("estado"));
            String fecha = rs.getString("fecha");
            String ip = rs.getString("ip");
            String puerto = rs.getString("puerto");

            a = new Actuador(id, dato, estado, fecha, ip, puerto);
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
                String qry = "SELECT * FROM actuadores WHERE dispositivo_id=?";
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

