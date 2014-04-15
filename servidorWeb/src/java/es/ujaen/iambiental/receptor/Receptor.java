/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.receptor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
        Date fecha = new Date();

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

                fecha.setTime((long)Integer.parseInt(splitChain[0])*1000);

                // Obtenemos el checksum en el servidor
                checksum = (Integer.parseInt(splitChain[0]) 
                        + Integer.parseInt(splitChain[1]) 
                        + Integer.parseInt(splitChain[2]));
                
                if (checksum == Integer.parseInt(splitChain[3])) {
                    System.out.println("Marca de tiempo: " + fecha.toString());
                    System.out.println("ID sensor: " + splitChain[1]);
                    System.out.println("Valor leido: " + splitChain[2]);
                    System.out.println("Checksum: " + splitChain[3]);
                    
                    // AQUI VA LA INSERCION EN LA BD
                } else {
                    // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                    System.out.println("El checksum no coincide");
                    
                    // AQUI LA SOLICITUD DE REENVIO DE LA ULTIMA LECTURA??
                }
            }
        }
    }
}
