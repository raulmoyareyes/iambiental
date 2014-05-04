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
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class EmisorUDP extends Thread {

    private DatagramSocket socket;
    private InetAddress ipAddress;
    private int puerto;
    private boolean ok;
    Interfaz interfaz;
    GeneradorTemperatura gt;
    Timestamp inicio;
    Timestamp fin;
    Long gap;

    /**
     * Constructor por defecto. Prepara el socket para los envíos
     *
     * @param ip Ip a mandar paquetes
     * @param puerto Puerto a mandar paquetes
     */
    public EmisorUDP(String ip, int puerto, Interfaz interfaz, GeneradorTemperatura gt) {
        try {
            this.ipAddress = InetAddress.getByName(ip);
            this.puerto = puerto;
            this.socket = new DatagramSocket();
            this.interfaz = interfaz;
            this.gt = gt;
            this.ok = true;
        } catch (Exception ex) {
            Logger.getLogger(EmisorUDP.class.getName()).log(Level.SEVERE, null, ex);
            showMessageDialog(null, ex.toString() + "\n" + ip + ":" + Integer.toString(puerto), "Cómorl?!", 0);
            this.ok = false;
        }
    }

    public void setIntervalo(Timestamp inicio, Timestamp fin, Long gap) {
        this.inicio = inicio;
        this.fin = fin;
        this.gap = gap;
    }

    public void run() {

        try {
            Timestamp tiempo = new Timestamp(this.inicio.getTime());
            int contador = 0;
            while (tiempo.before(this.fin)) {
                Thread.sleep(10); //Para dar cierto retardo
                
                
                //Actualizar la fecha y la hora
                interfaz.setFechaHora(tiempo.toString());
                
                Float temp = gt.getTemperatura(tiempo.getTime());
                
                if(temp!=null){
                
                this.mandarMensajeUDP(temp.toString());
                this.interfaz.addLog("[Mensaje enviado]\n");
                }else{
                    
                this.interfaz.addLog("[Error          ]\n");
                }
                
                

                //Sumar el gap de tiempo
                tiempo.setTime(tiempo.getTime() + gap);
                ++contador;
            }

            showMessageDialog(null, "¡Simulación finalizada! " + contador + " paquete(s) enviado(s).\nRecuerda detener el simulador antes de volverlo a arrancar", "Éxito", 1);

        } catch (Exception ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
//            showMessageDialog(null, "Ha ocurrido un error.\nIntenta detener y volver a iniciar el simulador", "Error", 2);
        }
        
        //Reactivar botón
//        interfaz.
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
            DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, this.ipAddress, this.puerto);
            this.socket.send(paquete);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(EmisorUDP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
