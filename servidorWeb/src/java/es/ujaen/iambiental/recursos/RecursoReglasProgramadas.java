package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorDatos;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorPersistir;
import es.ujaen.iambiental.excepciones.ReglaProgramadaNoEncontrada;
import es.ujaen.iambiental.modelos.ReglaProgramada;
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
@Path("/reglasProgramadas")
@Component(value = "recursoReglasProgramadas")
public class RecursoReglasProgramadas {
    @Autowired
    AdminBean administrador;
    
    @GET
    @Path("/{idReglaProgramada}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerReglaProgramada(@PathParam("idReglaProgramada") Integer idReglaProgramada) {
        ReglaProgramada reglaProgramada = administrador.obtenerReglaProgramada(idReglaProgramada);
        if (reglaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla programada no encontrada.").build()
            );
        }
        return Response.ok(reglaProgramada).build();

    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public Response listarReglasProgramadas() {
        return Response.ok(administrador.listarReglasProgramadas()).build();
    }
    
    @PUT
    @Path("/{idTareaProgramada}")
    @Consumes("application/json")
    public Response crearReglaProgramada(ReglaProgramada reglaProgramada, @PathParam("idTareaProgramada") String idTareaProgramada) {
        if (reglaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto regla programada.").build()
            );
        }

        try {
            administrador.crearReglaProgramada(reglaProgramada, idTareaProgramada);
        } catch (ReglaProgramadaErrorDatos | ReglaProgramadaErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear la regla programada.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE
    @Path("/{idReglaProgramada}")
    //@Consumes("application/json")
    public Response eliminarReglaProgramada(@PathParam("idReglaProgramada") Integer idReglaProgramada) {
        ReglaProgramada reglaProgramada = administrador.obtenerReglaProgramada(idReglaProgramada);
        if (reglaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla programada no encontrada.").build()
            );
        } else {
            try {
                administrador.eliminarReglaProgramada(idReglaProgramada);
            } catch (ReglaProgramadaErrorEliminar | ReglaProgramadaNoEncontrada e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar la regla programada.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }
    
    @Path("/{idReglaProgramada}")
    @Consumes("application/json")
    public Response modificarReglaProgramada(@PathParam("idReglaProgramada") Integer idReglaProgramada, ReglaProgramada reglaProgramada) {
        if (reglaProgramada == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto regla programada.").build()
            );
        }

        if (administrador.obtenerReglaProgramada(idReglaProgramada) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Regla programada no encontrada.").build()
            );
        }
        try {
            administrador.modificarReglaProgramada(reglaProgramada);
        } catch (ReglaProgramadaErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar la regla programada.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
