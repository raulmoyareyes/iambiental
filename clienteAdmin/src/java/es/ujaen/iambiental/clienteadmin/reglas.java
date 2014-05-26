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
import es.ujaen.iambiental.modelos.ReglaSensorActuador;
import es.ujaen.iambiental.modelos.Sensor;
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

        // Sensores
        ClientResponse responseJSONS = recurso.path("/sensores").accept("application/json").get(ClientResponse.class);
        List<Sensor> sensores = responseJSONS.getEntity(List.class);

        // Actuadores
        ClientResponse responseJSONA = recurso.path("/actuadores").accept("application/json").get(ClientResponse.class);
        List<Actuador> actuadores = responseJSONA.getEntity(List.class);

        // Reglas Sensor-Actuador
        ClientResponse responseJSONR = recurso.path("/reglasSensorActuador").accept("application/json").get(ClientResponse.class);
        List<ReglaSensorActuador> reglasSensorActuador = responseJSONR.getEntity(List.class);

        //Cabecera
        request.setAttribute("mainMenuOption", "reglas");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);

        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
                rd = request.getRequestDispatcher("/WEB-INF/reglas/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/insertar": //Insertar regla
                if (request.getParameter("crear") != null) {
                    //Insertar regla
                    String descripcion = request.getParameter("descripcion");
                    int estadoActuador = Integer.parseInt(request.getParameter("estadoActuador"));
                    float margenRuido = Float.parseFloat(request.getParameter("margenRuido"));
                    float valorMin = Float.parseFloat(request.getParameter("valorMin"));
                    float valorMax = Float.parseFloat(request.getParameter("valorMax"));
                    int sensor_id = Integer.parseInt(request.getParameter("sensor"));
                    int actuador_id = Integer.parseInt(request.getParameter("actuador"));
                    //Verificar valorMin y valorMax
                    if (valorMin > valorMax) {
                        float aux = valorMin;
                        valorMin = valorMax;
                        valorMax = aux;
                    }
                    //Buscar sensor y actuador expecífico
                    Sensor s = null;
                    for (int i = 0; i < sensores.size() && s == null; i++) {
                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                        if (aux.getId() == sensor_id) {
                            s = aux;
                        }
                    }
                    Actuador a = null;
                    for (int i = 0; i < actuadores.size() && a == null; i++) {
                        Actuador aux = mapper.convertValue(actuadores.get(i), Actuador.class);
                        if (aux.getId() == actuador_id) {
                            a = aux;
                        }
                    }

                    ReglaSensorActuador r = new ReglaSensorActuador(descripcion, s, a, valorMax, valorMin, margenRuido, estadoActuador);

                    recurso.path("/reglasSensorActuador")
                            .type("application/json")
                            .put(ClientResponse.class, r);

                    response.sendRedirect("/clienteAdmin/reglas");
                } else {
                    //Formulario nueva regla
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("actuadores", actuadores);
                    request.setAttribute("reglasSensorActuador", reglasSensorActuador);
                    rd = request.getRequestDispatcher("/WEB-INF/reglas/insertar.jsp");
                    rd.include(request, response);
                }
                break;
            case "/ver": //Ver regla
                request.setAttribute("sensores", sensores);
                request.setAttribute("actuadores", actuadores);
                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
                int id = Integer.parseInt(request.getParameter("id"));
                ReglaSensorActuador r = null;
                for (int i = 0; i < reglasSensorActuador.size() && r == null; i++) { // no cambiar al for-loop
                    ReglaSensorActuador aux = mapper.convertValue(reglasSensorActuador.get(i), ReglaSensorActuador.class);
                    if (aux.getId() == id) {
                        r = aux;
                    }
                }
                request.setAttribute("reglaSensorActuador", r);
                rd = request.getRequestDispatcher("/WEB-INF/reglas/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //regla eliminada
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                r = null;
                for (int i = 0; i < reglasSensorActuador.size() && r == null; i++) {
                    ReglaSensorActuador aux = mapper.convertValue(reglasSensorActuador.get(i), ReglaSensorActuador.class);
                    if (aux.getId() == idEliminar) {
                        r = aux;
                    }
                }
                request.setAttribute("eliminado", r.getDescripcion());
                recurso.path("/reglasSensorActuador/" + idEliminar).delete();
                request.setAttribute("reglasSensorActuador", reglasSensorActuador);
                response.sendRedirect("/clienteAdmin/reglas");
                break;
            case "/editar": //Editar regla
                if (request.getParameter("modificar") != null) {

                    //Insertar regla
                    int idModificar = Integer.parseInt(request.getParameter("modificar"));
                    String descripcion = request.getParameter("descripcion");
                    int estadoActuador = Integer.parseInt(request.getParameter("estadoActuador"));
                    float margenRuido = Float.parseFloat(request.getParameter("margenRuido"));
                    float valorMin = Float.parseFloat(request.getParameter("valorMin"));
                    float valorMax = Float.parseFloat(request.getParameter("valorMax"));
                    int sensor_id = Integer.parseInt(request.getParameter("sensor"));
                    int actuador_id = Integer.parseInt(request.getParameter("actuador"));
                    //Verificar valorMin y valorMax
                    if (valorMin > valorMax) {
                        float aux = valorMin;
                        valorMin = valorMax;
                        valorMax = aux;
                    }
                    //Buscar sensor y actuador expecífico
                    Sensor s = null;
                    for (int i = 0; i < sensores.size() && s == null; i++) {
                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                        if (aux.getId() == sensor_id) {
                            s = aux;
                        }
                    }
                    Actuador a = null;
                    for (int i = 0; i < actuadores.size() && a == null; i++) {
                        Actuador aux = mapper.convertValue(actuadores.get(i), Actuador.class);
                        if (aux.getId() == actuador_id) {
                            a = aux;
                        }
                    }

                    r = null;
                    for (int i = 0; i < reglasSensorActuador.size() && r == null; i++) {
                        ReglaSensorActuador aux = mapper.convertValue(reglasSensorActuador.get(i), ReglaSensorActuador.class);
                        if (aux.getId() == idModificar) {
                            r = aux;
                        }
                    }

                    r.setDescripcion(descripcion);
                    r.setEstadoActuador(estadoActuador);
                    r.setMargenRuido(margenRuido);
                    r.setValorMin(valorMin);
                    r.setValorMax(valorMax);
                    r.setSensor(s);
                    r.setActuador(a);

                    recurso.path("/reglasSensorActuador/" + idModificar)
                            .type("application/json")
                            .post(ClientResponse.class, r);

                    response.sendRedirect("/clienteAdmin/reglas/ver?id="+idModificar);
                } else {
                    //Formulario editar regla
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    r = null;
                    for (int i = 0; i < reglasSensorActuador.size() && r == null; i++) {
                        ReglaSensorActuador aux = mapper.convertValue(reglasSensorActuador.get(i), ReglaSensorActuador.class);
                        if (aux.getId() == idEditar) {
                            r = aux;
                        }
                    }
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("actuadores", actuadores);
                    request.setAttribute("reglasSensorActuador", reglasSensorActuador);
                    request.setAttribute("reglaSensorActuador", r);
                    rd = request.getRequestDispatcher("/WEB-INF/reglas/editar.jsp");
                    rd.include(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/reglas/modalEliminar.jsp");
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
