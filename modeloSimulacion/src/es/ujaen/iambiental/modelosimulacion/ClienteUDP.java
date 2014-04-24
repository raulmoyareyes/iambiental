/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.modelosimulacion;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Constructor por defecto
     * @param ip
     * @param puerto 
     */
    public ClienteUDP(String ip, int puerto) {
        try {
            this.ipAddress = InetAddress.getByName(ip);
            this.puerto = puerto;
            this.socket = new DatagramSocket();
            this.ok = true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
            showMessageDialog(null, ex.toString() + "\n" + ip + "\n" + Integer.toString(puerto), "Cómorl?!", 0);
        }
    }

    public boolean isOk() {
        return ok;
    }

}
