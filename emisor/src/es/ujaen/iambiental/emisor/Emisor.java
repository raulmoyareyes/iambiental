package es.ujaen.iambiental.emisor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicente
 */
public class Emisor {
    /**
     * Envia un paquete a Emisor con lo necesario para reenviar al Arduino objetivo
     * @param paquete
     * @throws Exception 
     */
    private static void envioActuador(String paquete, String ip, String puerto) throws Exception {
        DatagramSocket socketActuador = new DatagramSocket(Integer.parseInt(puerto));
        InetAddress direccionIP = InetAddress.getByName(ip);
        
        byte[] datosEnvio= new byte[1024];
        
        datosEnvio = paquete.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8902);
        socketActuador.send(paqueteEnvio);
    }
    
    /**
     * Función hash MD5 de 16 caracteres
     * @param id
     * @param dato
     * @param estado
     * @param fecha
     * @param ip
     * @param puerto
     * @return hast en String
     */
    private static String hashMD5(int id, float dato, int estado, int fecha, String ip, String puerto) {
        String hash = "";
        
        try {
            String text = "a" + ";" 
                    + String.valueOf(id) + ";" 
                    + String.valueOf(dato) + ";" 
                    + String.valueOf(estado) + ";" 
                    + String.valueOf(fecha) + ";" 
                    + ip + ";" 
                    + puerto + ";";
            MessageDigest msg = MessageDigest.getInstance("MD5");
            msg.update(text.getBytes(), 0, text.length());
            String digest1 = new BigInteger(1, msg.digest()).toString(16);
            digest1 = digest1.substring(0, 16);
            
            hash = digest1;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Emisor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return hash;
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
        String checksum;
        
        
        while (true) {
            try {
                DatagramPacket paqueteRecepcion =
                        new DatagramPacket(datosRecepcion, datosRecepcion.length);

                // Espera a recibir algo
                socketServidor.receive(paqueteRecepcion);

                // Esperamos el mensaje con la lectura del sensor
                socketServidor.receive(paqueteRecepcion);
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

                    // Obtenemos el checksum en el servidor
                    // id, dato, estado, fecha
                    String text = "a" + ";" 
                            + String.valueOf(id) + ";" 
                            + String.valueOf(dato) + ";" 
                            + String.valueOf(estado) + ";" 
                            + String.valueOf(fecha) + ";"
                            + ip + ";" 
                            + puerto + ";";

                    // Hallamos el checksum mediante la función
                    checksum = hashMD5(id, dato, estado, fecha, ip, puerto);

                    if (checksum.equals(splitChain[7])) {
                        // Acabamos de construir el paquete con el checksum 
                        // y lo enviamos al Emisor para que se encargue
                        // de enviarlo al Arduino
                        text = text + checksum;
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
