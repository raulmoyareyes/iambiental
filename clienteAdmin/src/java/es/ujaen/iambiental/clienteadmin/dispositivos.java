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
import es.ujaen.iambiental.modelos.Dispositivo;
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
 * @author Gabri
 */
@WebServlet(name = "dispositivos", urlPatterns = {"/dispositivos/*"})
public class dispositivos extends HttpServlet {

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
        
        // Dispositivos
        ClientResponse responseJSOND = recurso.path("/dispositivos").accept("application/json").get(ClientResponse.class);
        List<Dispositivo> dispositivos = responseJSOND.getEntity(List.class);

        //Cabecera
        request.setAttribute("mainMenuOption", "dispositivos");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);

        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("dispositivos", dispositivos);
                rd = request.getRequestDispatcher("/WEB-INF/dispositivos/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dispositivos/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/insertar": //Insertar dispositivo
                if(request.getParameter("crear") != null){
                    String descripcion = request.getParameter("descripcion");
                    String ip = request.getParameter("ip");
                    String puerto = request.getParameter("puerto");
                    recurso.path("/dispositivos")
                                .type("application/json")
                                .put(ClientResponse.class, new Dispositivo(descripcion, ip, puerto));
                    
                    response.sendRedirect("/clienteAdmin/dispositivos");
                }else{
                    request.setAttribute("dispositivos", dispositivos);
                    rd = request.getRequestDispatcher("/WEB-INF/dispositivos/insertar.jsp");
                    rd.include(request, response);
                }
                break;
            case "/ver": //Ver dispositivo
                request.setAttribute("dispositivos", dispositivos);
                int id = Integer.parseInt(request.getParameter("id"));
                Dispositivo d = new Dispositivo();
                for (int i = 0; i < dispositivos.size(); i++) {
                        Dispositivo aux = mapper.convertValue(dispositivos.get(i), Dispositivo.class);
                        if (aux.getId() == id) {
                            d = aux;
                        }
                    }
                request.setAttribute("dispositivo", d);
                rd = request.getRequestDispatcher("/WEB-INF/dispositivos/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/dispositivos/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Dispositivo eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                System.out.println("id: "+idEliminar);
                d = new Dispositivo();
                for (int i = 0; i < dispositivos.size(); i++) {
                    Dispositivo aux = mapper.convertValue(dispositivos.get(i), Dispositivo.class);
                    if (aux.getId() == idEliminar) {
                        d = aux;
                    }
                }
                request.setAttribute("eliminado", d.getDescripcion());
                recurso.path("/dispositivos/" + idEliminar).delete();
                request.setAttribute("dispositivos", dispositivos);
                response.sendRedirect("/clienteAdmin/dispositivos");
                break;
            case "/editar": //Editar dispositivo
                if (request.getParameter("modificar") != null) {
                    id = Integer.parseInt(request.getParameter("modificar"));
                    String descripcion = request.getParameter("descripcion");
                    String ip = request.getParameter("ip");
                    String puerto = request.getParameter("puerto");
                    
                    System.out.println("id: "+id);

                    recurso.path("/dispositivos/" + id)
                            .type("application/json")
                            .post(ClientResponse.class, new Dispositivo(id, descripcion, ip, puerto));

                    response.sendRedirect("/clienteAdmin/dispositivos");
                } else {
                    request.setAttribute("dispositivos", dispositivos);
                    d = new Dispositivo();
                    id = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < dispositivos.size(); i++) {
                        Dispositivo aux = mapper.convertValue(dispositivos.get(i), Dispositivo.class);
                        if (aux.getId() == id) {
                            d = aux;
                        }
                    }
                    request.setAttribute("dispositivo", d);
                    rd = request.getRequestDispatcher("/WEB-INF/dispositivos/editar.jsp");
                    rd.include(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/dispositivos/modalEliminar.jsp");
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
