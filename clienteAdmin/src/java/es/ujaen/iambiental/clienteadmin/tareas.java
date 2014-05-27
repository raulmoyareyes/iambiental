package es.ujaen.iambiental.clienteadmin;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.ReglaProgramada;
import es.ujaen.iambiental.modelos.Sensor;
import es.ujaen.iambiental.modelos.TareaProgramada;
import java.io.IOException;
import java.util.ArrayList;
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

        // Sensores
        ClientResponse responseJSONS = recurso.path("/sensores").accept("application/json").get(ClientResponse.class);
        List<Sensor> sensores = responseJSONS.getEntity(List.class);

        // Actuadores
        ClientResponse responseJSONA = recurso.path("/actuadores").accept("application/json").get(ClientResponse.class);
        List<Actuador> actuadores = responseJSONA.getEntity(List.class);

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
                    String cron = request.getParameter("cron");
                    String[] reglasensor = request.getParameterValues("sensores");
                    String[] reglaactuador = request.getParameterValues("actuadores");
                    String[] descripcionRegla = request.getParameterValues("descripcionRegla"); //CUIDAO EL JSP
                    String[] valorMin = request.getParameterValues("valorMin");
                    String[] valorMax= request.getParameterValues("valorMax");
                    String[] margenRuido = request.getParameterValues("margenRuido");
                    String[] estadoActuador = request.getParameterValues("estadoActuador");
                    List<ReglaProgramada> reglas = new ArrayList();
                    for (int i = 0; i < reglasensor.length; i++) {
                        Sensor s = null;
                        for (int j = 0; j < sensores.size() && s == null; j++) {
                            Sensor aux = mapper.convertValue(sensores.get(j), Sensor.class);
                            if (aux.getId() == Integer.parseInt(reglasensor[i])) {
                                s = aux;
                            }
                        }
                        Actuador a = null;
                        for (int j = 0; j < actuadores.size() && a == null; j++) {
                            Actuador aux = mapper.convertValue(actuadores.get(j), Actuador.class);
                            if (aux.getId() == Integer.parseInt(reglaactuador[i])) {
                                a = aux;
                            }
                        }
                        
                        reglas.add(new ReglaProgramada(descripcionRegla[i] ,s, a,
                                Float.parseFloat(valorMin[i]), 
                                Float.parseFloat(valorMax[i]),
                                Float.parseFloat(margenRuido[i]),
                                Integer.parseInt(estadoActuador[i])));
                    }
                    
                    TareaProgramada tarea = new TareaProgramada(descripcion, reglas, cron);

                    recurso.path("/tareasProgramadas")
                            .type("application/json")
                            .put(ClientResponse.class, tarea);

                    response.sendRedirect("/clienteAdmin/tareas");
                } else {
                    request.setAttribute("tareas", tareas);
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("actuadores", actuadores);
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
            case "/editar": //Editar tarea programada
                if (request.getParameter("modificar") != null) {
                    id = Integer.parseInt(request.getParameter("modificar"));
                    String descripcion = request.getParameter("descripcion");
                    String cron = request.getParameter("cron");
                    String[] reglasensor = request.getParameterValues("sensores");
                    String[] reglaactuador = request.getParameterValues("actuadores");
                    String[] descripcionRegla = request.getParameterValues("descripcionRegla"); //CUIDAO EL JSP
                    String[] valorMin = request.getParameterValues("valorMin");
                    String[] valorMax= request.getParameterValues("valorMax");
                    String[] margenRuido = request.getParameterValues("margenRuido");
                    String[] estadoActuador = request.getParameterValues("estadoActuador");
                    List<ReglaProgramada> reglas = new ArrayList();
                    for (int i = 0; i < reglasensor.length; i++) {
                        Sensor s = null;
                        for (int j = 0; j < sensores.size() && s == null; j++) {
                            Sensor aux = mapper.convertValue(sensores.get(j), Sensor.class);
                            if (aux.getId() == Integer.parseInt(reglasensor[i])) {
                                s = aux;
                            }
                        }
                        Actuador a = null;
                        for (int j = 0; j < actuadores.size() && a == null; j++) {
                            Actuador aux = mapper.convertValue(actuadores.get(j), Actuador.class);
                            if (aux.getId() == Integer.parseInt(reglaactuador[i])) {
                                a = aux;
                            }
                        }
                        
                        reglas.add(new ReglaProgramada(descripcionRegla[i] ,s, a,
                                Float.parseFloat(valorMin[i]), 
                                Float.parseFloat(valorMax[i]),
                                Float.parseFloat(margenRuido[i]),
                                Integer.parseInt(estadoActuador[i])));
                    }
                    
                    t = null;
                    for (int i = 0; i < tareas.size() && t == null; i++) {
                        TareaProgramada aux = mapper.convertValue(tareas.get(i), TareaProgramada.class);
                        if (aux.getId() == id) {
                            t = aux;
                        }
                    }
                    
                    t.setCron(cron);
                    t.setDescripcion(descripcion);
                    t.setReglasProgramadas(reglas);
                    
                    recurso.path("/tareasProgramadas")
                            .type("application/json")
                            .post(ClientResponse.class, t);

                    response.sendRedirect("/clienteAdmin/tareas");
                } else {
                    t = null;
                    id = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < tareas.size() && t == null; i++) {
                        TareaProgramada aux = mapper.convertValue(tareas.get(i), TareaProgramada.class);
                        if (aux.getId() == id) {
                            t = aux;
                        }
                    }
                    request.setAttribute("tarea", t);
                    request.setAttribute("tareas", tareas);
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("actuadores", actuadores);
                    rd = request.getRequestDispatcher("/WEB-INF/tareas/editar.jsp");
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
