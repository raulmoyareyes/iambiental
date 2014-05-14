package es.ujaen.iambiental.emisorWeb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import es.ujaen.iambiental.modelos.Actuador;
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
public class EmisorWeb {
    /**
     * Envia un paquete a Emisor con lo necesario para reenviar al Arduino objetivo
     * @param a Recibe un objeto Actuador
     * @throws Exception 
     */
    public static void envioActuador(Actuador a) throws Exception {
        DatagramSocket socketActuador = new DatagramSocket(Integer.parseInt(a.getPuerto()));
        InetAddress direccionIP = InetAddress.getByName(a.getIp());
        
        String hash = "";
        String text = "";
        
        try {
            text = "a" + ";" 
                + String.valueOf(a.getId()) + ";" 
                + String.valueOf(a.getDato()) + ";" 
                + String.valueOf(a.getEstado()) + ";" 
                + String.valueOf(a.getFecha().getSeconds()) + ";" 
                + a.getIp() + ";" 
                + a.getPuerto() + ";";
            MessageDigest msg = MessageDigest.getInstance("MD5");
            msg.update(text.getBytes(), 0, text.length());
            String digest1 = new BigInteger(1, msg.digest()).toString(16);
            digest1 = digest1.substring(0, 16);
            
            hash = digest1;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EmisorWeb.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        byte[] datosEnvio= new byte[1024];
        
        text = text + hash;
        
        datosEnvio = text.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8902);
        socketActuador.send(paqueteEnvio);
    }
}
