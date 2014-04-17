/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.clienteadmin;

import es.ujaen.iambiental.modelos.Dependencia_provisional;
import java.io.IOException;
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
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
@WebServlet(name = "dependencias", urlPatterns = {"/dependencias/*"})
public class dependencias extends HttpServlet {

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
        request.setAttribute("mainMenuOption", "dependencias");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);

        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("dependencias", dependencias);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/insertar": //Insertar dependencia
                request.setAttribute("dependencias", dependencias);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/insertar.jsp");
                rd.include(request, response);
                break;
            case "/ver": //Ver dependencia
                request.setAttribute("dependencias", dependencias);
                request.setAttribute("dependencia", dependencias.get(Integer.parseInt(request.getParameter("id"))));
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Dependencia eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("eliminado", dependencias.get(idEliminar).getNombre());
                dependencias.remove(idEliminar); //¿Comprobar si hay error?
                request.setAttribute("dependencias", dependencias);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/editar": //Insertar dependencia
                request.setAttribute("dependencias", dependencias);
                request.setAttribute("dependencia", dependencias.get(Integer.parseInt(request.getParameter("id"))));
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/editar.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
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
