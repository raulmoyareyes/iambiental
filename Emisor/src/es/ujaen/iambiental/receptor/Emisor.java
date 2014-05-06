/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.receptor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Vicente
 */
public class Emisor {
    private void envioSensor(int id, float dato, String descripcion, int estado, 
            String fecha, String ip, String puerto, int dependencia_id, int tipo) throws Exception {
        
        DatagramSocket socketSensor = new DatagramSocket(8902);
        InetAddress direccionIP = InetAddress.getByName("http://kefren.ujaen.es:6919");
        
        byte[] datosEnvio= new byte[1024];
                
        Integer checksum = Integer.valueOf("s") + id + ((int) dato) + Integer.valueOf(descripcion) 
                + estado + Integer.valueOf(fecha) + Integer.valueOf(ip) 
                + Integer.valueOf(puerto) + dependencia_id + tipo;
        String mensaje = "s" + id + dato + descripcion + estado + fecha
                + ip + puerto + dependencia_id + tipo + checksum;
        
        datosEnvio = mensaje.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8902);
        socketSensor.send(paqueteEnvio);
    }
    
    private void envioActuador(int id, float dato, String descripcion, int estado, 
            String fecha, String ip, String puerto, int dependencia_id, int tipo) throws Exception {
        
        DatagramSocket socketActuador = new DatagramSocket(8902);
        InetAddress direccionIP = InetAddress.getByName("http://kefren.ujaen.es:6919");
        
        byte[] datosEnvio= new byte[1024];
                
        Integer checksum = Integer.valueOf("a") + id + ((int) dato) + Integer.valueOf(descripcion) 
                + estado + Integer.valueOf(fecha) + Integer.valueOf(ip) 
                + Integer.valueOf(puerto) + dependencia_id + tipo;
        String mensaje = "a" + id + dato + descripcion + estado + fecha
                + ip + puerto + dependencia_id + tipo + checksum;
        
        datosEnvio = mensaje.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8902);
        socketActuador.send(paqueteEnvio);
    }
    
    public static void main(String[] args) throws Exception {

    }
}
