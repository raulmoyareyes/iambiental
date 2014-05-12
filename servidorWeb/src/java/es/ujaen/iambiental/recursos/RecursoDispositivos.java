package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.ActuadorErrorCambiarDispositivo;
import es.ujaen.iambiental.excepciones.DispositivoErrorActualizar;
import es.ujaen.iambiental.excepciones.DispositivoErrorDatos;
import es.ujaen.iambiental.excepciones.DispositivoErrorEliminar;
import es.ujaen.iambiental.excepciones.DispositivoErrorPersistir;
import es.ujaen.iambiental.excepciones.DispositivoNoEncontrado;
import es.ujaen.iambiental.excepciones.SensorErrorCambiarDispositivo;
import es.ujaen.iambiental.modelos.Dispositivo;
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
 *  Recurso REST para el cliente de administración
 * 
 * @author Gabriel
 */
@Path("/dispositivos")
@Component(value = "recursoDispositivos")
public class RecursoDispositivos {
    
    @Autowired
    AdminBean administrador;
    
    @GET
    @Path("/{idDispositivo}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerDispositivo(@PathParam("idDispositivo") Integer idDispositivo) {
        Dispositivo dispositivo = administrador.obtenerDispositivo(idDispositivo);
        if (dispositivo == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dispositivo no encontrado.").build()
            );
        }
        return Response.ok(dispositivo).build();
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public ArrayList<Dispositivo> listarDispositivos() {
        return new ArrayList(administrador.listarDispositivos().values());

    }
    
    @PUT
    @Consumes("application/json")
    public Response crearDispositivo(Dispositivo dispositivo) {
        if (dispositivo == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto dispositivo.").build()
            );
        }

        try {
            administrador.crearDispositivo(dispositivo);
        } catch (DispositivoErrorDatos | DispositivoErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear el dispositivo.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE
    @Path("/{idDispositivo}")
    public Response eliminarDispositivo(@PathParam("idDispositivo") Integer idDispositivo) throws SensorErrorCambiarDispositivo, ActuadorErrorCambiarDispositivo {
        Dispositivo dispositivo = administrador.obtenerDispositivo(idDispositivo);
        if (dispositivo == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dispositivo no encontrado.").build()
            );
        } else {
            try {
                administrador.eliminarDispositivo(idDispositivo);
            } catch (DispositivoErrorEliminar | DispositivoNoEncontrado e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar el dispositivo.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }
    
    @POST
    @Path("/{idDispositivo}")
    @Consumes("application/json")
    public Response modificarDispositivo(@PathParam("idDispositivo") Integer idDispositivo, Dispositivo dispositivo) {
        if (dispositivo == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto dispositivo.").build()
            );
        }

        if (administrador.obtenerDispositivo(idDispositivo) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dispositivo no encontrado.").build()
            );
        }
        try {
            administrador.modificarDispositivo(dispositivo);
        } catch (DispositivoErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar el dispositivo.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
}
