package es.ujaen.iambiental.clienteadmin;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.ujaen.iambiental.modelos.TareaProgramada;
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
 * @author agustin
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@WebServlet(name = "tareas", urlPatterns = {"/tareas/*"})
public class tareas extends HttpServlet {

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

        // Tareas Programadas
        ClientResponse responseJSOND = recurso.path("/tareasProgramadas").accept("application/json").get(ClientResponse.class);
        List<TareaProgramada> tareas = responseJSOND.getEntity(List.class);

        //Cabecera
        request.setAttribute("mainMenuOption", "tareas");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);

        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("tareas", tareas);
                rd = request.getRequestDispatcher("/WEB-INF/tareas/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/tareas/modalEliminar.jsp");
                rd.include(request, response);
                break;

            case "/insertar": //Insertar tarea programada
                if (request.getParameter("crear") != null) {
                    String descripcion = request.getParameter("descripcion");
                    
                    recurso.path("/tareasProgramadas")
                            .type("application/json")
                            .put(ClientResponse.class, new TareaProgramada(descripcion));

                    response.sendRedirect("/clienteAdmin/tareas");
                } else {
                    request.setAttribute("tareas", tareas);
                    rd = request.getRequestDispatcher("/WEB-INF/tareas/insertar.jsp");
                    rd.include(request, response);
                }
                break;
            case "/ver": //Ver tarea programada
                request.setAttribute("tareas", tareas);
                int id = Integer.parseInt(request.getParameter("id"));
                TareaProgramada t = null;
                for (int i = 0; i < tareas.size() && t == null; i++) {
                    TareaProgramada aux = mapper.convertValue(tareas.get(i), TareaProgramada.class);
                    if (aux.getId() == id) {
                        t = aux;
                    }
                }
                request.setAttribute("tarea", t);
                rd = request.getRequestDispatcher("/WEB-INF/tareas/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/tareas/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Tarea programada eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                t = null;
                for (int i = 0; i < tareas.size() && t == null; i++) {
                    TareaProgramada aux = mapper.convertValue(tareas.get(i), TareaProgramada.class);
                    if (aux.getId() == idEliminar) {
                        t = aux;
                    }
                }
                request.setAttribute("eliminado", t.getDescripcion());
                recurso.path("/tareasProgramadas/" + idEliminar).delete();
                request.setAttribute("tareas", tareas);
                response.sendRedirect("/clienteAdmin/tareas");
                break;
            case "/editar": //Editar sensor
//                if (request.getParameter("modificar") != null) {
//                    id = Integer.parseInt(request.getParameter("modificar"));
//                    int idFisico = Integer.parseInt(request.getParameter("idFisico"));
//                    String descripcion = request.getParameter("descripcion");
//                    int idD = Integer.parseInt(request.getParameter("dependencia"));
//                    Dependencia d = null;
//                    for (int i = 0; i < dependencias.size() && d == null; i++) {
//                        Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
//                        if (aux.getId() == idD) {
//                            d = aux;
//                        }
//                    }
//                    String ip = request.getParameter("ip");
//                    String puerto = request.getParameter("puerto");
//                    int tipo = Integer.parseInt(request.getParameter("tipo"));
//
//                    s = null;
//                    for (int i = 0; i < sensores.size() && s == null; i++) {
//                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
//                        if (aux.getId() == id) {
//                            s = aux;
//                        }
//                    }
//                    s.setDescripcion(descripcion);
//                    s.setDependencia(d);
//                    s.setIp(ip);
//                    s.setPuerto(puerto);
//                    s.setIdFisico(idFisico);
//                    s.setTipo(tipo);
//
//                    recurso.path("/sensores/" + id)
//                            .type("application/json")
//                            .post(ClientResponse.class, s);
//
//                    response.sendRedirect("/clienteAdmin/sensores");
//                } else {
//                    request.setAttribute("sensores", sensores);
//                    request.setAttribute("dependencias", dependencias);
//                    s = null;
//                    id = Integer.parseInt(request.getParameter("id"));
//                    for (int i = 0; i < sensores.size() && s == null; i++) {
//                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
//                        if (aux.getId() == id) {
//                            s = aux;
//                        }
//                    }
//                    request.setAttribute("sensor", s);
//                    rd = request.getRequestDispatcher("/WEB-INF/sensores/editar.jsp");
//                    rd.include(request, response);
//                    rd = request.getRequestDispatcher("/WEB-INF/sensores/modalEliminar.jsp");
//                    rd.include(request, response);
//                }
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
