/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.modelosimulacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class GeneradorTemperatura {

    ///Mapa de <Fecha,Temperatura>
    Map<Long, Float> temperaturas;

    /**
     * Constructor
     */
    public GeneradorTemperatura() {
        temperaturas = new HashMap<>();
        try {
            //Datos extraidos de las lecturas de temperaturas de la Universidad de Jaén en el año 2013
            //Datos en blanco introducidos a mano
            String sCadena;
            BufferedReader bf = new BufferedReader(new FileReader("./lecturasTemperaturaUJA.txt"));
            Date d;
            while ((sCadena = bf.readLine()) != null) {
                //Split de la cadena en formato "mes;dia;hora;minuto;segundo;temperatura"
                String[] arrayString = sCadena.split(";");
                d = new Date(
                                0, //Año (Año - 1900)
                                Integer.parseInt(arrayString[0]) - 1,//Mes
                                Integer.parseInt(arrayString[1]),//Día
                                Integer.parseInt(arrayString[2]),//Hora
                                Integer.parseInt(arrayString[3]),//Minuto
                                Integer.parseInt(arrayString[4]) //Segundo
                        );
                temperaturas.put(d.getTime(), Float.parseFloat(arrayString[5])); //Temperatura
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneradorTemperatura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR AL CREAR EL GENERADOR DE TEMPERATURAS");
        }
    }

    public Float getTemperatura(Long tiempo) {
        Timestamp t = new Timestamp(tiempo);
        t.setYear(0);
        if (this.temperaturas.containsKey(t.getTime())) {
            return this.temperaturas.get(t.getTime());
        } else {
            return null;
        }
    }

    private void leerFicheroTemperaturasUJA(String fichero) {
        try {
            String sCadena;
            BufferedReader bf = new BufferedReader(new FileReader(fichero));
            //Descartar las 2 primeras lineas
            sCadena = bf.readLine();
            sCadena = bf.readLine();
            //Recorrer el fichero
            while ((sCadena = bf.readLine()) != null) {

                //Split de la cadena
                String[] arrayString = sCadena.split("\t");

                //Fecha y Hora
                String[] fecha = arrayString[0].split("/");
                String[] hora = arrayString[1].split(":");
                //Temperatura
                Double temp = (Double.parseDouble(arrayString[2]) + Double.parseDouble(arrayString[3])) / 2;

                //Mostrar cadena
//                temperaturas.put(new Date(1900, 1, 28, 0, 1, 30), 5.3f);
                System.out.println("temperaturas.put(new Date(0, " + fecha[1] + ", " + fecha[0] + ", " + hora[0] + ", " + hora[1] + ", 0), " + temp.toString() + "f);");

            }

        } catch (Exception ex) {
            Logger.getLogger(GeneradorTemperatura.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR!");
        }
    }
}
