/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.receptor;

import static es.ujaen.iambiental.daos.ReceptorDAO.closeConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.openConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.lecturaSensorBD;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import sun.management.Sensor;

/**
 *
 * @author Vicente_2
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
        Date fecha = new Date();
        Timestamp time_stamp = null;

        while (true) {
            DatagramPacket paqueteRecepcion =
                    new DatagramPacket(datosRecepcion, datosRecepcion.length);
            
            socketServidor.receive(paqueteRecepcion);
            
            mensaje = new String(paqueteRecepcion.getData());
            
            if (mensaje == null) {
                System.out.println("Mensaje vacío");
            } else {
                System.out.println("Conexión confirmada con el mensaje de prueba: " + mensaje);
                
                // Esperamos el mensaje con la lectura del sensor
                socketServidor.receive(paqueteRecepcion);
                mensaje = new String(paqueteRecepcion.getData());
            
                // Parte la cadena devuelta por Arduino que contiene varios campos
                // separador por ";"
                splitChain = mensaje.split(";");
 
                // Si recibimos un paquete Sensor o Actuador se trata de manera diferente
                if (splitChain[0].compareTo("s") == 0) {
                    // Pasamos de segundos a milisegundos ya que llega asi desde Arduino
                    fecha.setTime((long)Integer.parseInt(splitChain[4])*1000);
                    time_stamp = Timestamp.valueOf(String.valueOf(fecha.getTime()));
                    
                    // Obtenemos el checksum en el servidor
                    // id, dato, descripcion, estado, fecha, ip, puerto, dependencia_id
                    checksum = (Integer.parseInt(splitChain[0]) 
                            + Integer.parseInt(splitChain[1]) 
                            + Integer.parseInt(splitChain[2])
                            + Integer.parseInt(splitChain[3])
                            + Integer.parseInt(splitChain[4])
                            + Integer.parseInt(splitChain[5])
                            + Integer.parseInt(splitChain[6])
                            + Integer.parseInt(splitChain[7])
                            + Integer.parseInt(splitChain[8])
                            );

                } else { // Sino pues Actuador
                    // Obtenemos el checksum en el servidor
                    // id, dato, dependencia, descripcion, estado, fecha, ip, puerto
                    checksum = (Integer.parseInt(splitChain[0]) 
                            + Integer.parseInt(splitChain[1]) 
                            + Integer.parseInt(splitChain[2])
                            + Integer.parseInt(splitChain[3])
                            + Integer.parseInt(splitChain[4])
                            + Integer.parseInt(splitChain[5])
                            + Integer.parseInt(splitChain[6])
                            + Integer.parseInt(splitChain[7])
                            + Integer.parseInt(splitChain[8])
                            );

                }
                
                if (checksum == Integer.parseInt(splitChain[9])) {
                    /*
                    System.out.println("Marca de tiempo: " + fecha.toString());
                    System.out.println("ID sensor: " + splitChain[1]);
                    System.out.println("Valor leido: " + splitChain[2]);
                    System.out.println("Checksum: " + splitChain[3]);
                    */
                                        
                    // AQUI VA LA INSERCION EN LA BD
                    //Boolean inserta = insertaDatosSensor(s);  
                    
                    // Comprobación de los datos insertados en BD
                    Connection cnx = openConexion();
                    Sensor s_aux = lecturaSensorBD(Integer.parseInt(splitChain[1])); 
                    
                    /*if (s_aux.checksum == splitChain[3] && inserta) {
                        System.out.println("Inserción realizada con éxito");
                    }*/
                    
                    closeConexion();
                    
                } else {
                    // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                    System.out.println("El checksum no coincide");
                    
                    // AQUI LA SOLICITUD DE REENVIO DE LA ULTIMA LECTURA??
}
            }
        }
    }
}
