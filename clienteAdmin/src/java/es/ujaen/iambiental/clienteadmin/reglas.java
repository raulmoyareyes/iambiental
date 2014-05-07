/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.clienteadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author agustin
 */
@WebServlet(name = "reglas", urlPatterns = {"/reglas/*"})
public class reglas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd;

        //Variables de las url del servidor
        String srvUrl = request.getContextPath() + request.getServletPath();
        request.setAttribute("srvUrl", srvUrl);
        request.setAttribute("appUrl", request.getContextPath());

        //Pathinfo
        String action = (request.getPathInfo() != null ? request.getPathInfo() : "");

        //Cliente para JSON
//        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
//        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
//        Client cliente = Client.create(defaultClientConfig);
//        WebResource recurso = null;
//        WebResource recurso = cliente.resource("http://localhost:8080/Hoteles-DAE-REST/recursos");
        /* SÓLO PARA PRUEBAS */
//        Sensor_provisional.reset(); //Resetear id de sensores
//        List<Sensor_provisional> sensores;
//        sensores = new ArrayList<>();
//        sensores.add(new Sensor_provisional("Sensor 1", 1, 10f, "192.168.1.10", "1111", 1));
//        sensores.add(new Sensor_provisional("Sensor 2", 2, 20f, "192.168.1.20", "2222", 2));
//        sensores.add(new Sensor_provisional("Sensor 3", 3, 30f, "192.168.1.30", "3333", 3));
//        sensores.add(new Sensor_provisional("Sensor 4", 4, 40f, "192.168.1.40", "4444", 4));
//        sensores.add(new Sensor_provisional("Sensor 5", 5, 50f, "192.168.1.50", "5555", 5));
//        sensores.add(new Sensor_provisional("Sensor 6", 6, 60f, "192.168.1.60", "6666", 6));
//        sensores.add(new Sensor_provisional("Sensor 7", 7, 70f, "192.168.1.70", "7777", 7));
//        
//        Actuador_provisional.reset(); //Resetear id de actuador
//        List<Actuador_provisional> actuadores;
//        actuadores = new ArrayList<>();
//        actuadores.add(new Actuador_provisional("Actuador 1", 1, 10f, "192.168.1.10", "1111", 1));
//        actuadores.add(new Actuador_provisional("Actuador 2", 2, 20f, "192.168.1.20", "2222", 2));
//        actuadores.add(new Actuador_provisional("Actuador 3", 3, 30f, "192.168.1.30", "3333", 3));
//        actuadores.add(new Actuador_provisional("Actuador 4", 4, 40f, "192.168.1.40", "4444", 4));
//        actuadores.add(new Actuador_provisional("Actuador 5", 5, 50f, "192.168.1.50", "5555", 5));
//        actuadores.add(new Actuador_provisional("Actuador 6", 6, 60f, "192.168.1.60", "6666", 6));
//        actuadores.add(new Actuador_provisional("Actuador 7", 7, 70f, "192.168.1.70", "7777", 7));
//        
//        ReglaSensorActuador_provisional.reset();
//        List<ReglaSensorActuador_provisional> reglasSensorActuador;
//        reglasSensorActuador = new ArrayList<>();
//        reglasSensorActuador.add(new ReglaSensorActuador_provisional("Regla 1", sensores.get(0), actuadores.get(0)));
//        reglasSensorActuador.add(new ReglaSensorActuador_provisional("Regla 2", sensores.get(1), actuadores.get(2)));
//        reglasSensorActuador.add(new ReglaSensorActuador_provisional("Regla 3", sensores.get(2), actuadores.get(2)));
//        
//        Dependencia_provisional.reset(); //Resetear id de dependencia
//        List<Dependencia_provisional> dependencias;
//        dependencias = new ArrayList<>();
//        dependencias.add(new Dependencia_provisional("Salón", "Descripción del salón"));
//        dependencias.add(new Dependencia_provisional("Cocina", "Descripción de cocina"));
//        dependencias.add(new Dependencia_provisional("Baño", "Descripción de baño"));
//        dependencias.add(new Dependencia_provisional("Dormitorio principal", "Descripción de dormitorio principal"));
//        dependencias.add(new Dependencia_provisional("Dormitorio individual", "Descripción de dormitorio individual"));
//        dependencias.add(new Dependencia_provisional("Pasillo", "Descripción de pasillo"));
//        dependencias.add(new Dependencia_provisional("Piscina", "Descripción de piscina"));
        /* FIN DE PRUEBAS */

        //Cabecera
//        request.setAttribute("mainMenuOption", "reglas");
//        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
//        rd.include(request, response);
//        
//        //Cuerpo
//        switch (action) {
//            case "/listado":
//            default: //Ninguna opción seleccionada
//                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/index.jsp");
//                rd.include(request, response);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
//                rd.include(request, response);
//                break;
//            case "/insertar": //Insertar sensor
//                request.setAttribute("sensores", sensores);
//                request.setAttribute("actuadores", actuadores);
//                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/insertar.jsp");
//                rd.include(request, response);
//                break;
//            case "/ver": //Ver sensor
//                request.setAttribute("sensores", sensores);
//                request.setAttribute("actuadores", actuadores);
//                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
//                request.setAttribute("reglaSensorActuador", reglasSensorActuador.get(Integer.parseInt(request.getParameter("id"))));
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/ver.jsp");
//                rd.include(request, response);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
//                rd.include(request, response);
//                break;
//            case "/eliminar": //Sensor eliminado
//                int idEliminar = Integer.parseInt(request.getParameter("id"));
//                request.setAttribute("eliminado", reglasSensorActuador.get(idEliminar).getDescripcion());
//                reglasSensorActuador.remove(idEliminar); //¿Comprobar si hay error?
//                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/index.jsp");
//                rd.include(request, response);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
//                rd.include(request, response);
//                break;
//            case "/editar": //Editar sensor
//                request.setAttribute("sensores", sensores);
//                request.setAttribute("actuadores", actuadores);
//                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
//                request.setAttribute("reglaSensorActuador", reglasSensorActuador.get(Integer.parseInt(request.getParameter("id"))));
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/editar.jsp");
//                rd.include(request, response);
//                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
//                rd.include(request, response);
//                break;
//        }
//        
        //Footer
        rd = request.getRequestDispatcher("/WEB-INF/pie.jsp");
        rd.include(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
