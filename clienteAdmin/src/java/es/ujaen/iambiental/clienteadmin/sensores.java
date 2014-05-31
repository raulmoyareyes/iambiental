package es.ujaen.iambiental.clienteadmin;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.ujaen.iambiental.modelos.Dependencia;
import es.ujaen.iambiental.modelos.Sensor;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Raúl Moya Reyes <www.raulmoya.com>
 */
@WebServlet(name = "sensores", urlPatterns = {"/sensores/*"})
public class sensores extends HttpServlet {

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

        // Sensores
        ClientResponse responseJSONS = recurso.path("/sensores").accept("application/json").get(ClientResponse.class);
        List<Sensor> sensores = responseJSONS.getEntity(List.class);

        //Cabecera
        request.setAttribute("mainMenuOption", "sensores");
        rd = request.getRequestDispatcher("/WEB-INF/cabecera.jsp");
        rd.include(request, response);

        //Cuerpo
        switch (action) {
            case "/listado":
            default: //Ninguna opción seleccionada
                request.setAttribute("sensores", sensores);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/index.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/modalEliminar.jsp");
                rd.include(request, response);
                break;

            case "/insertar": //Insertar sensor
                if (request.getParameter("crear") != null) {
                    int idFisico = Integer.parseInt(request.getParameter("idFisico"));
                    String descripcion = request.getParameter("descripcion");
                    int idD = Integer.parseInt(request.getParameter("dependencia"));
                    Dependencia d = null;
                    for (int i = 0; i < dependencias.size() && d == null; i++) {
                        Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
                        if (aux.getId() == idD) {
                            d = aux;
                        }
                    }
                    String ip = request.getParameter("ip");
                    String puerto = request.getParameter("puerto");
                    int tipo = Integer.parseInt(request.getParameter("tipo"));

                    recurso.path("/sensores")
                            .type("application/json")
                            .put(ClientResponse.class, new Sensor(descripcion, d, ip, puerto, tipo, idFisico));

                    response.sendRedirect("/clienteAdmin/sensores");
                } else {
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("dependencias", dependencias);

                    rd = request.getRequestDispatcher("/WEB-INF/sensores/insertar.jsp");
                    rd.include(request, response);
                }
                break;
            case "/ver": //Ver sensor
                request.setAttribute("sensores", sensores);
                int id = Integer.parseInt(request.getParameter("id"));
                Sensor s = null;
                for (int i = 0; i < sensores.size() && s == null; i++) {
                    Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                    if (aux.getId() == id) {
                        s = aux;
                    }
                }
                request.setAttribute("sensor", s);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/ver.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/historico": //Ver histórico
                request.setAttribute("sensores", sensores);
                id = Integer.parseInt(request.getParameter("id"));
                s = null;
                for (int i = 0; i < sensores.size() && s == null; i++) {
                    Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                    if (aux.getId() == id) {
                        s = aux;
                    }
                }
                request.setAttribute("sensor", s);
                // Historico //new Date(114,03,28,12,00), new Date(115,04,30,14,00));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String fechaInicio = request.getParameter("fechaInicio");
                try {
                    fechaInicio = Long.toString(sdf.parse(fechaInicio).getTime());
                } catch (Exception ex) {
                    fechaInicio = "0";
                }
                
                String fechaFinal = request.getParameter("fechaFinal");
                try {
                    fechaFinal = Long.toString(sdf.parse(fechaFinal).getTime());
                } catch (Exception ex) {
                    fechaFinal = Long.toString(Long.MAX_VALUE);
                }
                
                
                ClientResponse responseJSONH = recurso.path("/sensores/" + id + "/historico")
                        .queryParam("fechaInicio", fechaInicio)
                        .queryParam("fechaFinal", fechaFinal)
                        .accept("application/json")
                        .get(ClientResponse.class);
                List<HistoricoSensores> historico = responseJSONH.getEntity(List.class);
                request.setAttribute("historico", historico);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/historico.jsp");
                rd.include(request, response);
                rd = request.getRequestDispatcher("/WEB-INF/sensores/modalEliminar.jsp");
                rd.include(request, response);
                break;
            case "/eliminar": //Sensor eliminado
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                s = null;
                for (int i = 0; i < sensores.size() && s == null; i++) {
                    Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                    if (aux.getId() == idEliminar) {
                        s = aux;
                    }
                }
                request.setAttribute("eliminado", s.getDescripcion());
                recurso.path("/sensores/" + idEliminar).delete();
                request.setAttribute("sensores", sensores);
                response.sendRedirect("/clienteAdmin/sensores");
                break;
            case "/editar": //Editar sensor
                if (request.getParameter("modificar") != null) {
                    id = Integer.parseInt(request.getParameter("modificar"));
                    int idFisico = Integer.parseInt(request.getParameter("idFisico"));
                    String descripcion = request.getParameter("descripcion");
                    int idD = Integer.parseInt(request.getParameter("dependencia"));
                    Dependencia d = null;
                    for (int i = 0; i < dependencias.size() && d == null; i++) {
                        Dependencia aux = mapper.convertValue(dependencias.get(i), Dependencia.class);
                        if (aux.getId() == idD) {
                            d = aux;
                        }
                    }
                    String ip = request.getParameter("ip");
                    String puerto = request.getParameter("puerto");
                    int tipo = Integer.parseInt(request.getParameter("tipo"));

                    s = null;
                    for (int i = 0; i < sensores.size() && s == null; i++) {
                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                        if (aux.getId() == id) {
                            s = aux;
                        }
                    }
                    s.setDescripcion(descripcion);
                    s.setDependencia(d);
                    s.setIp(ip);
                    s.setPuerto(puerto);
                    s.setIdFisico(idFisico);
                    s.setTipo(tipo);

                    recurso.path("/sensores/" + id)
                            .type("application/json")
                            .post(ClientResponse.class, s);

                    response.sendRedirect("/clienteAdmin/sensores");
                } else {
                    request.setAttribute("sensores", sensores);
                    request.setAttribute("dependencias", dependencias);
                    s = null;
                    id = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < sensores.size() && s == null; i++) {
                        Sensor aux = mapper.convertValue(sensores.get(i), Sensor.class);
                        if (aux.getId() == id) {
                            s = aux;
                        }
                    }
                    request.setAttribute("sensor", s);
                    rd = request.getRequestDispatcher("/WEB-INF/sensores/editar.jsp");
                    rd.include(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/sensores/modalEliminar.jsp");
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
