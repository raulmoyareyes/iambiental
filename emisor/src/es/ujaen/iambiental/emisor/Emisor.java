package es.ujaen.iambiental.emisor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicente
 */
public class Emisor {
    /**
     * Envia un paquete al Arduino con lo necesario para hacer su acción
     * @param paquete
     * @throws Exception 
     */
    private static void envioActuador(String paquete, String ip, String puerto) throws Exception {
        DatagramSocket socketActuador = new DatagramSocket();
        InetAddress direccionIP = InetAddress.getByName(ip);
        
        byte[] datosEnvio= new byte[1024];
        
        datosEnvio = paquete.getBytes();
        DatagramPacket paqueteEnvio = 
                new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, Integer.parseInt(puerto));
        socketActuador.send(paqueteEnvio);
    }
    
    public static void main(String[] args) throws Exception {
        // Crea socket datagrama en el puerto 8901
        DatagramSocket socketServidor = new DatagramSocket(8901);
        byte[] datosRecepcion = new byte[1024];
    
        String[] splitChain;
        String mensaje;
        // Atributos principales
        int id;
        float dato;
        int estado;
        int fecha;
        String ip;
        String puerto;
        int checksum;
        
        while (true) {
            try {
                DatagramPacket paqueteRecepcion =
                        new DatagramPacket(datosRecepcion, datosRecepcion.length);

                // Espera a recibir algo
                socketServidor.receive(paqueteRecepcion);
                System.out.println("Mensaje entrante en Emisor");
                mensaje = new String(paqueteRecepcion.getData());

                // Parte la cadena devuelta por Arduino que contiene varios campos
                // separador por ";"
                splitChain = mensaje.split(";");

                // Si recibimos un paquete Sensor o Actuador se trata de manera diferente
                if (splitChain[0].compareTo("s") == 0) {     
                    // Por ahora creo que solo se necesita soporte para Actuadores
                } else { // Sino pues Actuador
                    // Asignamos los datos extraidos a sus respectivas variables
                    id = Integer.parseInt(splitChain[1]);
                    dato = Float.parseFloat(splitChain[2]);
                    estado = Integer.parseInt(splitChain[3]);
                    fecha = Integer.parseInt(splitChain[4]);
                    ip = splitChain[5];
                    puerto = splitChain[6];
                    
                    // Hallamos el checksum mediante la función
                    checksum = 0 + id + (int)dato + estado + Integer.parseInt(splitChain[4]);
                    if (checksum<0) checksum *= -1;
                    if (checksum == Integer.parseInt(splitChain[7].trim())) {
                        // Acabamos de construir el paquete con el checksum 
                        // y lo enviamos al Emisor para que se encargue
                        // de enviarlo al Arduino
                        String text = "a" + ";" 
                            + String.valueOf(id) + ";" 
                            + String.valueOf(dato) + ";" 
                            + String.valueOf(estado) + ";" 
                            + String.valueOf(fecha) + ";"
                            + checksum;
                        envioActuador(text, ip, puerto);
                    } else {
                        System.out.println("Error en el checksum");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Emisor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
