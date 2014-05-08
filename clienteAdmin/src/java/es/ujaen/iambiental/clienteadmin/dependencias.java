/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.clienteadmin;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Dependencia;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

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
        
        // Mapeador
        ObjectMapper mapper = new ObjectMapper();
        
        //Cliente para JSON
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        Client cliente = Client.create(defaultClientConfig);
        WebResource recurso = cliente.resource("http://localhost:8084/servidorWeb/recursos");
        
        // Dependencias
        ClientResponse responseJSOND = recurso.path("/dependencias").accept("application/json").get(ClientResponse.class);
        List<Dependencia> dependencias = responseJSOND.getEntity(List.class);

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
                if(request.getParameter("crear") != null){
                    // Recoger la dependencia
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");
                    
                    recurso.path("/dependencias")
                                .type("application/json")
                                .put(ClientResponse.class, new Dependencia(nombre, descripcion));
                    
                    response.sendRedirect("/clienteAdmin/dependencias");
                }else{
                    request.setAttribute("dependencias", dependencias);
                    rd = request.getRequestDispatcher("/WEB-INF/dependencias/insertar.jsp");
                    rd.include(request, response);
                }
                break;
            case "/ver": //Ver dependencia
                request.setAttribute("dependencias", dependencias);
                int id = Integer.parseInt(request.getParameter("id"));
                Dependencia d = new Dependencia();
                for (int i = 0; i < dependencias.size(); i++) {
                        Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
                        if (aux.getId() == id) {
                            d = aux;
                        }
                    }
                request.setAttribute("dependencia", d);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Dependencia eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                d = new Dependencia();
                for (int i = 0; i < dependencias.size(); i++) {
                    Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
                    if (aux.getId() == idEliminar) {
                        d = aux;
                    }
                }
                request.setAttribute("eliminado", d.getNombre());
                recurso.path("/dependencias/" + idEliminar).delete();
                request.setAttribute("dependencias", dependencias);
                response.sendRedirect("/clienteAdmin/dependencias");
                break;
            case "/editar": //Insertar dependencia
                if (request.getParameter("modificar") != null) {
                    id = Integer.parseInt(request.getParameter("modificar"));
                    String nombre = request.getParameter("nombre");
                    String descripcion = request.getParameter("descripcion");

                    recurso.path("/dependencias/" + id)
                            .type("application/json")
                            .post(ClientResponse.class, new Dependencia(id, nombre, descripcion));

                    response.sendRedirect("/clienteAdmin/dependencias");
                } else {
                    request.setAttribute("dependencias", dependencias);
                    d = new Dependencia();
                    id = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < dependencias.size(); i++) {
                        Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
                        if (aux.getId() == id) {
                            d = aux;
                        }
                    }
                    request.setAttribute("dependencia", d);
                    rd = request.getRequestDispatcher("/WEB-INF/dependencias/editar.jsp");
                    rd.include(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/dependencias/modalEliminar.jsp");
                    rd.include(request, response);
                }
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
