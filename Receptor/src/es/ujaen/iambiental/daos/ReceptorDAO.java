/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.modelos.Sensor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    private static String coonPoolName = "iAmbiental";

    /**
     * Abre la conexion con la BD
     * @return Devuelve la conexion
     */
    public static Connection openConexion() {
        cnx = null;
        try {

            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("jdbc/" + coonPoolName);
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
     * @param s Instancia de Sensor donde se encuentran los datos
     * @return Devuelve 'true' o 'false' dependiendo del éxito de la operación.
     */
    public static boolean insertaDatosSensor(Sensor s) {
        boolean salida = false;
        if (openConexion() != null) {
            try {
                String qry = "INSERT INTO actuadores(id, descripcion, dependencia,"
                        + "fecha, dato, ip, puerto, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmn = cnx.prepareStatement(qry);

                // Atributos de sensor y el time_stamp en segundos
                stmn.setString(1, String.valueOf(s.getID()));
                stmn.setString(2, String.valueOf(s.getDescripcion()));
                stmn.setString(3, String.valueOf(s.getDependencia()));
                stmn.setString(4, String.valueOf(s.getFecha()));
                stmn.setString(5, String.valueOf(s.getDato()));
                stmn.setString(6, String.valueOf(s.getIp()));
                stmn.setString(7, String.valueOf(s.getPuerto()));
                stmn.setString(8, String.valueOf(s.getEstado()));

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
        Sensor s = null;
        try {
            //int id, String descripcion, Dependencia dependencia, Date fecha,
            //float dato, String ip, String puerto, int estado          
            s = new Sensor(Integer.valueOf(rs.getString("id")), rs.getString("descripcion"),
                    rs.getString("dependencia"), rs.getString("fecha")),
                    rs.getString("dato"), rs.getString("ip")),
                    rs.getString("puerto"), rs.getString("estado"));
        } catch (Exception ex) {
            Logger.getLogger(ReceptorDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }
    
    /**
     * Recuperamos una lectura del sensor
     * @param id
     * @return 
     */
    public static Sensor lecturaSensorBD (int id_sensor) {
        Sensor s = null;
        if (openConexion() != null) {
            try {
                String qry = "SELECT * FROM preguntas WHERE id=?";
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
}

