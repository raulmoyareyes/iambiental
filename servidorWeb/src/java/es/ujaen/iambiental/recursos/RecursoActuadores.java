package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.ActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ActuadorErrorDatos;
import es.ujaen.iambiental.excepciones.ActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ActuadorErrorPersistir;
import es.ujaen.iambiental.excepciones.ActuadorNoEncontrado;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.util.Date;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gabriel
 */
@Path("/actuadores")
@Component(value = "recursoActuadores")
public class RecursoActuadores {
    
    @Autowired
    AdminBean administrador;
    
    @GET
    @Path("/{idActuador}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerActuador(@PathParam("idActuador") String idActuador) {
        Actuador actuador = administrador.obtenerActuador(idActuador);
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
            );
        }
        return Response.ok(actuador).build();

    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public Response listarActuadores() {
        return Response.ok(administrador.listarActuadores()).build();
    }
    
    @PUT
    @Path("/{idActuador}")
    @Consumes("application/json")
    public Response crearActuador(@PathParam("idActuador") String idActuador, Actuador actuador) {
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto actuador.").build()
            );
        }

        if (administrador.obtenerActuador(idActuador) != null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT).entity("Actuador existente.").build()
            );
        }
        try {
            administrador.crearActuador(actuador);
        } catch (ActuadorErrorDatos | ActuadorErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear el actuador.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE
    @Path("/{idActuador}")
    //@Consumes("application/json")
    public Response eliminarActuador(@PathParam("idActuador") String idActuador) {
        Actuador actuador = administrador.obtenerActuador(idActuador);
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
            );
        } else {
            try {
                administrador.eliminarActuador(idActuador);
            } catch (ActuadorErrorEliminar | ActuadorNoEncontrado e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar el actuador.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }
    
    @Path("/{idActuador}")
    @Consumes("application/json")
    public Response modificarActuador(@PathParam("idActuador") String idActuador, Actuador actuador) {
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto actuador.").build()
            );
        }

        if (administrador.obtenerActuador(idActuador) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
            );
        }
        try {
            administrador.modificarActuador(actuador);
        } catch (ActuadorErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar el actuador.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @GET
    @Path("/{idActuador}/{fechaInicio}/{fechaFinal}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerHistoricoActuador(@QueryParam("idActuador") String idActuador, @QueryParam("fechaInicio") Date fechaInicio, @QueryParam("fechaFinal") Date fechaFinal) {
        Actuador actuador = administrador.obtenerActuador(idActuador);
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
            );
        } else {
            Map<Date, Double> historico = administrador.obtenerHistoricoActuador(idActuador, fechaInicio, fechaFinal);
            if (historico == null) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
                );
            }
            return Response.ok(historico).build();
        }
    }
    
}
