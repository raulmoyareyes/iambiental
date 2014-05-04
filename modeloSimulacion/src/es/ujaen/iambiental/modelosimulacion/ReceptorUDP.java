/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.modelosimulacion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class ReceptorUDP extends Thread {

    ///Log de UDP
    Interfaz interfaz;
    ///IP
    private String ip;
    ///Puerto
    private Integer puerto;
    ///Receptor es OK
    private boolean ok;

    /**
     * Constructor
     *
     * @param ip IP del receptor
     * @param puerto puerto del receptor
     * @param txtLogUDP JTextArea de log de UDP
     */
    public ReceptorUDP(String ip, Integer puerto, Interfaz interfaz) {
        if (ip != null) {
            this.ip = ip;
            this.puerto = puerto;
            this.ok = true;
            this.interfaz = interfaz;
            this.interfaz.setLblDatosServidorUDP(this.ip + ":" + this.puerto.toString());
        } else {
            this.ok = false;
        }
    }

    /**
     * Método run() del hilo
     */
    public void run() {

        Date date = new Date(System.currentTimeMillis());
        if (this.isOk()) {
            interfaz.addLogServidorUDP("[" + date.toString() + "]Servidor arrancado en " + this.ip + ":" + this.puerto.toString() + "\n");

            try {
                DatagramSocket serverSocket = new DatagramSocket(this.puerto);
                byte[] receiveData = new byte[1024];
//                byte[] sendData = new byte[1024];
                while (true) {
                    DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(paqueteRecibido);
                    String mensaje = new String(paqueteRecibido.getData()).substring(0, paqueteRecibido.getLength());

                    date = new Date(System.currentTimeMillis());
                    interfaz.addLogServidorUDP("[" + date.toString() + "]Mensaje recibido:\t" + mensaje + "\n");
//                    Mensaje Feedback
//                    System.out.println("RECEIVED: " + sentence + "#");
//                    InetAddress IPAddress = receivePacket.getAddress();
//                    int port = receivePacket.getPort();
//                    String capitalizedSentence = sentence.toUpperCase();
//                    sendData = capitalizedSentence.getBytes();
//                    DatagramPacket sendPacket
//                            = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//                    serverSocket.send(sendPacket);
                }
            } catch (Exception e) {
                date = new Date(System.currentTimeMillis());
                interfaz.addLogServidorUDP("[" + date.toString() + "]Ha ocurrido un error al recibir un paquete UDP\n");
            }

        } else {
            interfaz.addLogServidorUDP("[" + date.toString() + "]No ha sido posible arrancar el servidor\n");
        }
    }

    /**
     * Indica si el receptor está correcto
     * @return true si el receptor UDP está correcto o false en caso contrario
     */
    public boolean isOk() {
        return ok;
    }

}
