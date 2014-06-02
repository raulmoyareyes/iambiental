package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorDatos;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorPersistir;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorNoEncontrada;
import es.ujaen.iambiental.modelos.ReglaSensorActuador;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gabriel
 */
@Path("/reglasSensorActuador")
@Component(value = "recursoReglasSensorActuador")
public class RecursoReglasSensorActuador {

    @Autowired
    AdminBean administrador;

    @GET
    @Path("/{idReglaSensorActuador}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerReglaSensorActuador(@PathParam("idReglaSensorActuador") Integer idReglaSensorActuador) {
        ReglaSensorActuador reglaSensorActuador = administrador.obtenerReglaSensorActuador(idReglaSensorActuador);
        if (reglaSensorActuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla sensor-actuador no encontrada.").build()
            );
        }
        return Response.ok(reglaSensorActuador).build();

    }

    @GET
    @Produces("application/json; charset=utf-8")
    public ArrayList<ReglaSensorActuador> listarReglasActuadorSensor() {
        return new ArrayList(administrador.listarReglasSensorActuador().values());
    }

    @PUT
    @Consumes("application/json")
    public Response crearReglaSensorActuador(ReglaSensorActuador reglaSensorActuador) {
        if (reglaSensorActuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto regla sensor-actuador.").build()
            );
        }

        try {
            administrador.crearReglaSensorActuador(reglaSensorActuador);
        } catch (ReglaSensorActuadorErrorDatos | ReglaSensorActuadorErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear la regla sensor-actuador.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("/{idReglaSensorActuador}")
    //@Consumes("application/json")
    public Response eliminarReglaSensorActuador(@PathParam("idReglaSensorActuador") Integer idReglaSensorActuador) {
        ReglaSensorActuador reglaSensorActuador = administrador.obtenerReglaSensorActuador(idReglaSensorActuador);
        if (reglaSensorActuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla sensor-actuador no encontrada.").build()
            );
        } else {
            try {
                administrador.eliminarReglaSensorActuador(idReglaSensorActuador);
            } catch (ReglaSensorActuadorErrorEliminar | ReglaSensorActuadorNoEncontrada e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar la regla sensor-actuador.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }

    @POST
    @Path("/{idReglaSensorActuador}")
    @Consumes("application/json")
    public Response modificarReglaSensorActuador(@PathParam("idReglaSensorActuador") Integer idReglaSensorActuador, ReglaSensorActuador reglaSensorActuador) {
        if (reglaSensorActuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto regla sensor-actuador.").build()
            );
        }

        if (administrador.obtenerReglaSensorActuador(idReglaSensorActuador) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla sensor-actuador no encontrada.").build()
            );
        }
        try {
            administrador.modificarReglaSensorActuador(reglaSensorActuador);
        } catch (ReglaSensorActuadorErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar la regla sensor-actuador.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

}
