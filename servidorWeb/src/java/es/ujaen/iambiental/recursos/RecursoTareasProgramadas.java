package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorDatos;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorPersistir;
import es.ujaen.iambiental.excepciones.TareaProgramadaNoEncontrada;
import es.ujaen.iambiental.modelos.TareaProgramada;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("/tareasProgramadas")
@Component(value = "recursoTareasProgramadas")
public class RecursoTareasProgramadas {
    
    @Autowired
    AdminBean administrador;
    
    @GET
    @Path("/{idTareaProgramada}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerTareaProgramada(@PathParam("idTareaProgramada") String idTareaProgramada) {
        TareaProgramada tareaProgramada = administrador.obtenerTareaProgramada(idTareaProgramada);
        if (tareaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Tarea programada no encontrada.").build()
            );
        }
        return Response.ok(tareaProgramada).build();

    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public Response listarTareasProgramadas() {
        return Response.ok(administrador.listarTareasProgramadas()).build();
    }
    
    @PUT
    @Path("/{idTareaProgramada}")
    @Consumes("application/json")
    public Response crearTareaProgramada(@PathParam("idTareaProgramada") String idTareaProgramada, TareaProgramada tareaProgramada) {
        if (tareaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto tarea programada.").build()
            );
        }

        if (administrador.obtenerTareaProgramada(idTareaProgramada) != null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT).entity("Tarea programada existente.").build()
            );
        }
        try {
            administrador.crearTareaProgramada(tareaProgramada);
        } catch (TareaProgramadaErrorDatos | TareaProgramadaErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear la tarea programada.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE
    @Path("/{idTareaProgramada}")
    //@Consumes("application/json")
    public Response eliminarTareaProgramada(@PathParam("idTareaProgramada") String idTareaProgramada) {
        TareaProgramada tareaProgramada = administrador.obtenerTareaProgramada(idTareaProgramada);
        if (tareaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Tarea programada no encontrada.").build()
            );
        } else {
            try {
                administrador.eliminarTareaProgramada(idTareaProgramada);
            } catch (TareaProgramadaErrorEliminar | TareaProgramadaNoEncontrada e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar la tarea programada.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }
    
    @Path("/{idTareaProgramada}")
    @Consumes("application/json")
    public Response modificarTareaProgramada(@PathParam("idTareaProgramada") String idTareaProgramada, TareaProgramada tareaProgramada) {
        if (tareaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto tarea programada.").build()
            );
        }

        if (administrador.obtenerTareaProgramada(idTareaProgramada) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Tarea programada no encontrada.").build()
            );
        }
        try {
            administrador.modificarTareaProgramada(tareaProgramada);
        } catch (TareaProgramadaErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar la tarea programada.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
}
