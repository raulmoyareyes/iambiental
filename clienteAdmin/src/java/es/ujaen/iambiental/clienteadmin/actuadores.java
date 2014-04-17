/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.clienteadmin;

import es.ujaen.iambiental.modelos.Actuador_provisional;
import es.ujaen.iambiental.modelos.Dependencia_provisional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agustin
 */
@WebServlet(name = "actuadores", urlPatterns = {"/actuadores/*"})
public class actuadores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        Actuador_provisional.reset(); //Resetear id de actuador
        List<Actuador_provisional> actuadores;
        actuadores = new ArrayList<>();
        actuadores.add(new Actuador_provisional("Actuador 1", 1, 10f, "192.168.1.10", "1111", 1));
        actuadores.add(new Actuador_provisional("Actuador 2", 2, 20f, "192.168.1.20", "2222", 2));
        actuadores.add(new Actuador_provisional("Actuador 3", 3, 30f, "192.168.1.30", "3333", 3));
        actuadores.add(new Actuador_provisional("Actuador 4", 4, 40f, "192.168.1.40", "4444", 4));
        actuadores.add(new Actuador_provisional("Actuador 5", 5, 50f, "192.168.1.50", "5555", 5));
        
        
        Dependencia_provisional.reset(); //Resetear id de dependencia
        List<Dependencia_provisional> dependencias;
        dependencias = new ArrayList<>();
        dependencias.add(new Dependencia_provisional("Salón", "Descripción del salón"));
        dependencias.add(new Dependencia_provisional("Cocina", "Descripción de cocina"));
        dependencias.add(new Dependencia_provisional("Baño", "Descripción de baño"));
        dependencias.add(new Dependencia_provisional("Dormitorio principal", "Descripción de dormitorio principal"));
        dependencias.add(new Dependencia_provisional("Dormitorio individual", "Descripción de dormitorio individual"));
        dependencias.add(new Dependencia_provisional("Pasillo", "Descripción de pasillo"));
        dependencias.add(new Dependencia_provisional("Piscina", "Descripción de piscina"));
        /* FIN DE PRUEBAS */

        //Cabecera
        request.setAttribute("mainMenuOption", "actuadores");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);
        
        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("actuadores", actuadores);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/insertar": //Insertar dependencia
                request.setAttribute("actuadores", actuadores);
                request.setAttribute("dependencias", dependencias);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/insertar.jsp");
                rd.include(request, response);
                break;
            case "/ver": //Ver dependencia
                request.setAttribute("actuadores", actuadores);
                request.setAttribute("actuador", actuadores.get(Integer.parseInt(request.getParameter("id"))));
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Dependencia eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("eliminado", actuadores.get(idEliminar).getDescripcion());
                actuadores.remove(idEliminar); //¿Comprobar si hay error?
                request.setAttribute("actuadores", actuadores);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/editar": //Insertar dependencia
                request.setAttribute("actuadores", actuadores);
                request.setAttribute("dependencias", dependencias);
                request.setAttribute("actuador", actuadores.get(Integer.parseInt(request.getParameter("id"))));
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/editar.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/actuadores/modalEliminar.jsp");
                rd.include(request, response);
                break;
        }
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
