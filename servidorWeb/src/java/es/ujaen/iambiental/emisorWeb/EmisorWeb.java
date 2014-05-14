package es.ujaen.iambiental.emisorWeb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import es.ujaen.iambiental.modelos.Actuador;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Vicente
 */
public class EmisorWeb {

    /**
     * Envia un paquete a Emisor con lo necesario para reenviar al Arduino
     * objetivo
     *
     * @param a Recibe un objeto Actuador
     * @throws Exception
     */
    public static void envioActuador(Actuador a) throws Exception {
        DatagramSocket socketActuador = new DatagramSocket(Integer.parseInt(a.getPuerto()));
        InetAddress direccionIP = InetAddress.getByName(a.getIp());

        String text = "";
        int checksum = 0;

        text = "a" + ";"
            + String.valueOf(a.getId()) + ";"
            + String.valueOf(a.getDato()) + ";"
            + String.valueOf(a.getEstado()) + ";"
            + String.valueOf(a.getFecha().getSeconds());
        
        checksum = 0 + a.getId() + (int)a.getDato() 
                + a.getEstado() + a.getFecha().getSeconds();

        byte[] datosEnvio = new byte[1024];

        text = text + ";" + checksum;

        datosEnvio = text.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8901);
        socketActuador.send(paqueteEnvio);
    }
}
