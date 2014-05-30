package es.ujaen.iambiental.receptor;

import static es.ujaen.iambiental.daos.ReceptorDAO.actualizaDatosActuador;
import static es.ujaen.iambiental.daos.ReceptorDAO.actualizaDatosSensor;
import static es.ujaen.iambiental.daos.ReceptorDAO.closeConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.lecturaActuadorBD;
import static es.ujaen.iambiental.daos.ReceptorDAO.openConexion;
import static es.ujaen.iambiental.daos.ReceptorDAO.lecturaSensorBD;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vicente
 */
public class Receptor {

    /**
     * Envia un paquete a Emisor con lo necesario para reenviar al Arduino
     * objetivo
     *
     * @param paquete
     * @throws Exception
     */
    private static void envioActuador(String paquete) throws Exception {
        DatagramSocket socketActuador = new DatagramSocket();
        // Definir la IP donde se encuentre el Emisor
        InetAddress direccionIP = InetAddress.getByName("localhost");

        byte[] datosEnvio = new byte[1024];

        datosEnvio = paquete.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionIP, 8901);
        socketActuador.send(paqueteEnvio);
        System.out.println("Mensaje enviado a Emisor");
    }

    public static void main(String[] args) throws Exception {
        // Crea socket datagrama en el puerto 8902
        DatagramSocket socketServidor
                = new DatagramSocket(8902);
        byte[] datosRecepcion = new byte[1024];

        String[] splitChain;
        String mensaje;
        int checksum;

        // Atributos principales
        int id;
        float dato;
        int estado;
        Date fecha = new Date();

        while (true) {
            try {
                DatagramPacket paqueteRecepcion
                        = new DatagramPacket(datosRecepcion, datosRecepcion.length);

                // Esperamos el mensaje con la lectura del sensor
                socketServidor.receive(paqueteRecepcion);
                System.out.println("Mensaje entrante");
                mensaje = new String(paqueteRecepcion.getData(), paqueteRecepcion.getOffset(), paqueteRecepcion.getLength());
                Boolean actualiza = false;

                // Parte la cadena devuelta por Arduino que contiene varios campos
                // separador por ";"
                splitChain = mensaje.split(";");

                // MENSAJE DE TESTEO
                System.out.println("El dispositivo con id: " + mensaje);
                // Si recibimos un paquete Sensor o Actuador se trata de manera diferente
                if (splitChain[0].compareTo("s") == 0) {    //Sensor
                    // Asignamos los datos extraidos a sus respectivas variables
                    id = Integer.parseInt(splitChain[1]);
                    dato = Float.parseFloat(splitChain[2]);
                    estado = Integer.parseInt(splitChain[3]);
                    // Pasamos de segundos a milisegundos ya que llega asi desde Arduino
                    fecha.setTime((long) Integer.parseInt(splitChain[4]) * 1000);
                    // Convertimos a tipo Timestamp para pasarlo a la funcion de insercion en BD
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                    String fechaSensor = sdf.format(fecha);

                    int fechaEnSecs = Integer.parseInt(splitChain[4]);
                    // Hallamos el checksum mediante la función
                    checksum = 0 + id + (int) dato + estado + fechaEnSecs;
                    if (checksum < 0) {
                        checksum *= -1;
                    }

                    // Actualización en BD
                    if (checksum == Integer.parseInt(splitChain[5].trim())) {
                        // Comprobamos si existe en BD
                        Connection cnx = openConexion();
                        String qry = "SELECT * FROM sensores WHERE idFisico=?";
                        PreparedStatement stmn = cnx.prepareStatement(qry);
                        stmn.setInt(1, id);
                        ResultSet rs = stmn.executeQuery();
                        // Una vez obtenido, si .next() que apunta al siguiente dato de la tupla
                        // está vacío, o sea, no se ha recibido nada, se ignora este mensaje
                        if (rs.next()) {
                            actualiza = actualizaDatosSensor(id, dato, estado, fecha);
                        } else {
                            System.out.println("Sensor no existente en BD");
                        }
                    } else {
                        System.out.println("Error en el checksum (" + checksum + ")");
                    }

                    // Comprobación de los datos insertados en BD
                    Sensor s_aux = lecturaSensorBD(Integer.parseInt(splitChain[1]));

                    if (actualiza && (s_aux.getIdFisico() == id)) {
                        System.out.println("Actualización realizada con éxito");

                        // COMPROBAMOS REGLAS SENSOR-ACTUADOR
                        System.out.println("Comprobando reglas...");

                        //Consultar en la BBDD todas las reglas que imputan al sensor
                        Connection cnx = openConexion();
                        String qry = "SELECT * FROM reglassensoractuador, actuadores WHERE reglassensoractuador.actuador_id = actuadores.idFisico AND sensor_id=?";
                        PreparedStatement stmn = cnx.prepareStatement(qry);
                        stmn.setInt(1, id);
                        ResultSet rs = stmn.executeQuery();
                        // Una vez obtenido, si .next() que apunta al siguiente dato de la tupla
                        // está vacío, o sea, no se ha recibido nada, se ignora este mensaje
                        while (rs.next()) {
                            float valorMin = Float.parseFloat(rs.getString("valorMin"));
                            float valorMax = Float.parseFloat(rs.getString("valorMax"));
                            float margenRuido = Float.parseFloat(rs.getString("margenRuido"));

                            //dato
                            int estadoCambio = Integer.parseInt(rs.getString("estadoActuador"));
                            int estadoActual = Integer.parseInt(rs.getString("estado"));

                            String ip = rs.getString("ip");
                            String puerto = rs.getString("puerto");
                            
                            boolean reglaSeCumple = false;

                            if (dato >= valorMax + margenRuido || dato <= valorMin - margenRuido) {
                                //Fuera de la regla
                                //ACTUALIZAR A VALOR 0
                                reglaSeCumple = false;
                            } else if (dato <= valorMax - margenRuido && dato >= valorMin + margenRuido) {
                                //En la regla plena
                                //ACTUALIZAR A VALOR DEL ESTADO
                                reglaSeCumple = true;
                            }
//                            else if (dato <= valorMax + margenRuido && dato >= valorMax - margenRuido) {
//                                //Dato en margen máximo. Hay que comprobar
//                            } else if (dato <= valorMin + margenRuido && dato >= valorMin - margenRuido) {
//                                //Dato en margen mínimo. Hay que comprobar
//                            }
//                            
                            if(reglaSeCumple){
                                System.out.println("REGLA SE CUMPLE");
                                //Valor a Cero
                                //ACTUALIZAR A VALOR DE LA REGLA
                                long fechaHora = new Date().getTime(); //Integer.parseInt(splitChain[4]);
                                String text = "a" + ";"
                                        + String.valueOf(id) + ";"
                                        + String.valueOf(dato) + ";"
                                        + String.valueOf(estadoCambio) + ";"
                                        + String.valueOf(fechaHora);

                                // Hallamos el checksum mediante la función
                                checksum = 0 + id + (int) dato + estadoCambio + (int) fechaHora;
                                if (checksum < 0) {
                                    checksum *= -1;
                                }

                                text = text + ";" + ip + ";" + puerto + ";" + checksum;
                                envioActuador(text);
                                actualizaDatosActuador(id, dato, estadoCambio, fecha);
                            }else{
                                System.out.println("REGLA NO SE CUMPLE");
                                //Valor de la regla
                                //ACTUALIZAR A VALOR POR DEFECTO (0)
//                                long fechaHora = new Date().getTime(); //Integer.parseInt(splitChain[4]);
//                                String text = "a" + ";"
//                                        + String.valueOf(id) + ";"
//                                        + String.valueOf(dato) + ";"
//                                        + "0" + ";"
//                                        + String.valueOf(fechaHora);

                                // Hallamos el checksum mediante la función
//                                checksum = 0 + id + (int) dato + 0 + (int) fechaHora;
//                                if (checksum < 0) {
//                                    checksum *= -1;
//                                }
//
//                                text = text + ";" + ip + ";" + puerto + ";" + checksum;
//                                envioActuador(text);
//                                actualizaDatosActuador(id, dato, 0, fecha);
                            }
                        }
                        System.out.println("Fin de comprobación de reglas.");
                        //FIN DE COMPROBACIÓN DE REGLAS SENSOR-ACTUADOR
                    } else {
                        // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                        System.out.println("Actualización no coincidente");
                    }

                } else { // Sino pues Actuador
                    // Asignamos los datos extraidos a sus respectivas variables
                    id = Integer.parseInt(splitChain[1]);
                    dato = Float.parseFloat(splitChain[2]);
                    estado = Integer.parseInt(splitChain[3]);
                    // Pasamos de segundos a milisegundos ya que llega asi desde Arduino
                    fecha.setTime((long) Integer.parseInt(splitChain[4]) * 1000);
                    // Convertimos a tipo Timestamp para pasarlo a la funcion de insercion en BD
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                    String fechaActuador = sdf.format(fecha);

                        // Obtenemos el checksum en el servidor
                    // id, dato, estado, fecha
                    String text = "a" + ";"
                            + String.valueOf(id) + ";"
                            + String.valueOf(dato) + ";"
                            + String.valueOf(estado) + ";"
                            + String.valueOf(splitChain[4]);
                    long fechaEnSecs = new Date().getTime(); //Integer.parseInt(splitChain[4]);

                    // Hallamos el checksum mediante la función
                    checksum = 0 + id + (int) dato + estado + (int) fechaEnSecs;
                    if (checksum < 0) {
                        checksum *= -1;
                    }

                    // Actualización en BD
                    if (checksum == Integer.parseInt(splitChain[5].trim())) {
                        // Comprobamos si existe en BD
                        Connection cnx = openConexion();
                        String qry = "SELECT * FROM actuadores WHERE idFisico=?";
                        PreparedStatement stmn = cnx.prepareStatement(qry);
                        stmn.setInt(1, id);
                        ResultSet rs = stmn.executeQuery();
                        rs.next();
                        // Obtenemos IP y puerto del arduino objetivo
                        String ip = rs.getString("ip");
                        String puerto = rs.getString("puerto");
                        //String ip ="192.168.9.250";
                        //String puerto = "8888";

                            // Acabamos de construir el paquete con el checksum 
                        // y lo enviamos al Emisor para que se encargue
                        // de enviarlo al Arduino
                        // Hallamos el checksum mediante la función
                        checksum = 0 + id + (int) dato + estado + (int) fechaEnSecs;
                        if (checksum < 0) {
                            checksum *= -1;
                        }
                        text = text + ";" + ip + ";" + puerto + ";" + checksum;
                        envioActuador(text);
                        actualiza = actualizaDatosActuador(id, dato, estado, fecha);
                        //System.out.println("Actuador no existente en BD");
                    } else {
                        System.out.println("Error en el checksum (" + checksum + ")");
                    }

                    // Comprobación de los datos insertados en BD
                    Actuador a_aux = lecturaActuadorBD(Integer.parseInt(splitChain[1]));

                    if (actualiza && (a_aux.getIdFisico() == id)) {
                        System.out.println("Actualización realizada con éxito");
                    } else {
                        // SE INFORMA DEL ERROR Y NO SE GUARDA EN BD
                        System.out.println("Actualización no coincidente");
                    }

                }

                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
