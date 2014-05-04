/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.receptor;

import static es.ujaen.iambiental.daos.ReceptorDAO.actualizaDatosActuador;
import static es.ujaen.iambiental.daos.ReceptorDAO.actualizaDatosSensor;
import static es.ujaen.iambiental.daos.ReceptorDAO.closeConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.lecturaActuadorBD;
import static es.ujaen.iambiental.daos.ReceptorDAO.openConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.lecturaSensorBD;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vicente
 */
public class Receptor {
    public static void main(String[] args) throws Exception {
        // Crea socket datagrama en el puerto 9876
        DatagramSocket socketServidor = 
                new DatagramSocket(8902);
        byte[] datosRecepcion = new byte[1024];
    
        String[] splitChain = null;
        String mensaje = "null";
        Integer checksum = null;
        

        // Atributos principales
        int id;
        float dato;
        int estado;
        Date fecha = new Date();
        
        while (true) {
            DatagramPacket paqueteRecepcion =
                    new DatagramPacket(datosRecepcion, datosRecepcion.length);
            
            // Espera a recibir algo
            socketServidor.receive(paqueteRecepcion);
            
            mensaje = new String(paqueteRecepcion.getData());
            
            if (mensaje == null) {
                System.out.println("Mensaje vacío");
            } else {
                System.out.println("Conexión confirmada con el mensaje de prueba: " + mensaje);
                
                // Esperamos el mensaje con la lectura del sensor
                socketServidor.receive(paqueteRecepcion);
                mensaje = new String(paqueteRecepcion.getData());
                Boolean actualiza = false;
            
                // Parte la cadena devuelta por Arduino que contiene varios campos
                // separador por ";"
                splitChain = mensaje.split(";");

                // Si recibimos un paquete Sensor o Actuador se trata de manera diferente
                if (splitChain[0].compareTo("s") == 0) {     
                    // Asignamos los datos extraidos a sus respectivas variables
                    id = Integer.parseInt(splitChain[1]);
                    dato = Float.parseFloat(splitChain[2]);
                    estado = Integer.parseInt(splitChain[4]);
                    // Pasamos de segundos a milisegundos ya que llega asi desde Arduino
                    fecha.setTime((long)Integer.parseInt(splitChain[5])*1000);
                    // Convertimos a tipo Timestamp para pasarlo a la funcion de insercion en BD
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                    String fechaSensor = sdf.format(fecha);
                    
                    // Obtenemos el checksum en el servidor
                    // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo(temp, sonido, etc)
                    checksum = (Integer.parseInt(splitChain[0]) 
                            + Integer.parseInt(splitChain[1]) 
                            + Integer.parseInt(splitChain[2])
                            + Integer.parseInt(splitChain[3])
                            + Integer.parseInt(splitChain[4])
                            + Integer.parseInt(splitChain[5])
                            + Integer.parseInt(splitChain[6])
                            + Integer.parseInt(splitChain[7])
                            + Integer.parseInt(splitChain[8])
                            + Integer.parseInt(splitChain[9])
                            );
                    // Actualización en BD
                    if (checksum == Integer.valueOf(splitChain[10])) {
                        actualiza = actualizaDatosSensor(id, dato, estado, fechaSensor);
                    } else {
                        System.out.println("Error en el checksum");
                    }
                    
                    // Comprobación de los datos insertados en BD
                    Connection cnx = openConexion();
                    Sensor s_aux = lecturaSensorBD(Integer.parseInt(splitChain[1])); 
                    
                    if (actualiza && (s_aux.getID() == id)) {
                        System.out.println("Actualización realizada con éxito");
                    } else {
                        // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                        System.out.println("Actualización no coincidente");
                    }

                } else { // Sino pues Actuador
                    // Asignamos los datos extraidos a sus respectivas variables
                    id = Integer.parseInt(splitChain[1]);
                    dato = Float.parseFloat(splitChain[2]);
                    estado = Integer.parseInt(splitChain[4]);
                    // Pasamos de segundos a milisegundos ya que llega asi desde Arduino
                    fecha.setTime((long)Integer.parseInt(splitChain[5])*1000);
                    // Convertimos a tipo Timestamp para pasarlo a la funcion de insercion en BD
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                    String fechaActuador = sdf.format(fecha);
                    
                    // Obtenemos el checksum en el servidor
                    // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo(temp, sonido, etc)
                    checksum = (Integer.parseInt(splitChain[0]) 
                            + Integer.parseInt(splitChain[1]) 
                            + Integer.parseInt(splitChain[2])
                            + Integer.parseInt(splitChain[3])
                            + Integer.parseInt(splitChain[4])
                            + Integer.parseInt(splitChain[5])
                            + Integer.parseInt(splitChain[6])
                            + Integer.parseInt(splitChain[7])
                            + Integer.parseInt(splitChain[8])
                            + Integer.parseInt(splitChain[9])
                            );
                    // Actualización en BD
                    if (checksum == Integer.valueOf(splitChain[10])) {
                        actualiza = actualizaDatosActuador(id, dato, estado, fechaActuador);
                    } else {
                        System.out.println("Error en el checksum");
                    }
                    
                    // Comprobación de los datos insertados en BD
                    Connection cnx = openConexion();
                    Actuador a_aux = lecturaActuadorBD(Integer.parseInt(splitChain[1])); 
                    
                    if (actualiza && (a_aux.getID() == id)) {
                        System.out.println("Actualización realizada con éxito");
                    } else {
                        // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                        System.out.println("Actualización no coincidente");
                    }
                }
                
                closeConexion();
            }
        }
    }
}
