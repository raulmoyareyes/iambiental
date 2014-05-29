package es.ujaen.iambiental.emisorWeb;

import es.ujaen.iambiental.modelos.Actuador;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     */
    public static void envioActuador(Actuador a) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socketActuador = new DatagramSocket();
        InetAddress direccionIP = InetAddress.getByName("localhost");

        String text;
        int checksum;

        System.out.println("Enviando paquete desde emisor web");

        text = "a" + ";"
                + String.valueOf(a.getIdFisico()) + ";"
                + String.valueOf(a.getDato()) + ";"
                + String.valueOf(a.getEstado()) + ";"
                + String.valueOf(a.getFecha().getTime()/1000) + ";"
                + String.valueOf(a.getIp()) + ";"
                + String.valueOf(a.getPuerto());

        checksum = 0 + a.getIdFisico() + (int) a.getDato()
                + a.getEstado() + (int)(a.getFecha().getTime()/1000);

        byte[] datosEnvio = new byte[1024];

        text = text + ";" + checksum;

        datosEnvio = text.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8901); //Puerto Emisor
        socketActuador.send(paqueteEnvio);
    }
}
