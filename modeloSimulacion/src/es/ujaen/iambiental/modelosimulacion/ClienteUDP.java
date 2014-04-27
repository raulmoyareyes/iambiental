/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.modelosimulacion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class ClienteUDP {

    private DatagramSocket socket;
    private InetAddress ipAddress;
    private int puerto;
    private boolean ok;

    /**
     * Constructor por defecto. Prepara el socket para los envíos
     *
     * @param ip Ip a mandar paquetes
     * @param puerto Puerto a mandar paquetes
     */
    public ClienteUDP(String ip, int puerto) {
        try {
            this.ipAddress = InetAddress.getByName(ip);
            this.puerto = puerto;
            this.socket = new DatagramSocket();
            this.ok = true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
            showMessageDialog(null, ex.toString() + "\n" + ip + ":" + Integer.toString(puerto), "Cómorl?!", 0);
            this.ok = false;
        }
    }

    /**
     * Comprueba si el cliente UDP está correctamente creado
     *
     * @return true si está listo para mandar mensajes o false en caso contrario
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Manda mensajes según la configuración de creación
     *
     * @param mensaje Mensaje a enviar
     * @return true si ha podido mandar el mensaje o false si ha ocurrido algún
     * error
     */
    public boolean mandarMensajeUDP(String mensajeString) {
        try {
            byte[] mensaje = mensajeString.getBytes();
            DatagramPacket paquete = new DatagramPacket(mensaje, mensajeString.length(), this.ipAddress, this.puerto);
            this.socket.send(paquete);
            this.socket.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
