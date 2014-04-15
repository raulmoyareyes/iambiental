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

        while (true) {
            DatagramPacket paqueteRecepcion =
                    new DatagramPacket(datosRecepcion, datosRecepcion.length);
            
            socketServidor.receive(paqueteRecepcion);
            
            mensaje = new String(paqueteRecepcion.getData());
            
            if (mensaje == null) {
                System.out.println("Mensaje vacío");
            } else {
                System.out.println("Conexión confirmada con el mensaje de prueba: " + mensaje);
            }
        }
    }
}
