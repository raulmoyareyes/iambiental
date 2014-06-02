package es.ujaen.iambiental.clienteuso.controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.ujaen.iambiental.clienteuso.modelos.Actuador;
import es.ujaen.iambiental.clienteuso.modelos.Dependencia;
import es.ujaen.iambiental.clienteuso.modelos.Sensor;
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
 * @author Raúl Moya Reyes
 */
@WebServlet(name = "ControladorPrincipal", urlPatterns = {"/c/*"})
public class ControladorPrincipal extends HttpServlet {

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

        String action = (request.getPathInfo() != null ? request.getPathInfo() : "");

        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        Client cliente = Client.create(defaultClientConfig);
        WebResource recurso = cliente.resource("http://localhost:8084/servidorWeb/recursos");

        switch (action) {
            case "": {
                ObjectMapper mapper = new ObjectMapper();

                /* Dependencias */
                ClientResponse responseJSOND = recurso.path("/dependencias").accept("application/json").get(ClientResponse.class);
                List<Dependencia> dependencias = responseJSOND.getEntity(List.class);
                request.setAttribute("dependencias", dependencias);
                
                Dependencia dependencia = mapper.convertValue(dependencias.get(0), Dependencia.class);

                /* Sensores */ // Seleccionar dependencia por defecto es 1
                ClientResponse responseJSONS = recurso.path("/sensores/dependencia/" + dependencia.getId()).accept("application/json").get(ClientResponse.class);
                List<Sensor> sensores = responseJSONS.getEntity(List.class);

                Sensor temperatura = new Sensor();
                for (int i = 0; i < sensores.size(); i++) {
                    // Hay que hacer la conversion ya que no se puede utilizar directamente.
                    Sensor s = mapper.convertValue(sensores.get(i), Sensor.class);
                    if (s.getTipo() == 1) {
                        temperatura = s;
                    }
                }
                request.setAttribute("temperatura", temperatura);

                /* Actuadores */
                ClientResponse responseJSONA = recurso.path("/actuadores/dependencia/"+dependencia.getId()).accept("application/json").get(ClientResponse.class);
                List<Actuador> actuadores = responseJSONA.getEntity(List.class);

                List<Actuador> actuadoresI = new ArrayList();
                Actuador termostato = new Actuador();
                for (int i = 0; i < actuadores.size(); i++) {
                    // Hay que hacer la conversion ya que no se puede utilizar directamente.
                    Actuador a = mapper.convertValue(actuadores.get(i), Actuador.class);
                    if (a.getTipo() == 1) {
                        actuadoresI.add(a);
                    } else if (a.getTipo() == 0) {
                        termostato = a;
                    }
                }
                request.setAttribute("termostato", termostato);
                request.setAttribute("actuadores", actuadoresI);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/clienteTactil.jsp");
                rd.forward(request, response);
                break;
            }

            case "/actuador": {
                String id = request.getParameter("id");
                String estado = request.getParameter("estado");
                String dato = request.getParameter("dato");

                ClientResponse responseJSONA = recurso.path("/actuadores/" + id).accept("application/json").get(ClientResponse.class);
                Actuador actuador = responseJSONA.getEntity(Actuador.class);

                if (actuador.getTipo() == 0) {
                    actuador.setDato(Float.parseFloat(dato));
                } else if (actuador.getTipo() == 1) {
                    actuador.setEstado(Integer.parseInt(estado));
                }

                recurso.path("/actuadores/" + id)
                        .type("application/json")
                        .post(ClientResponse.class, actuador);
                break;
            }
        }

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
