package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.beans.ClienteUsoBean;
import es.ujaen.iambiental.excepciones.ActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ActuadorErrorDatos;
import es.ujaen.iambiental.excepciones.ActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ActuadorErrorPersistir;
import es.ujaen.iambiental.excepciones.ActuadorNoEncontrado;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.HistoricoActuadores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @Autowired
    ClienteUsoBean clienteUso;

    @GET
    @Path("/{idActuador}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerActuador(@PathParam("idActuador") int idActuador) {
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
    public ArrayList<Actuador> listarActuadores() {
        return new ArrayList(administrador.listarActuadores().values());
    }

    @PUT
    @Consumes("application/json")
    public Response crearActuador(Actuador actuador) {
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto actuador.").build()
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
    public Response eliminarActuador(@PathParam("idActuador") int idActuador) {
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

    @POST
    @Path("/{idActuador}")
    @Consumes("application/json")
    public Response modificarActuador(@PathParam("idActuador") int idActuador, Actuador actuador) {
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

    // Repasar
    @GET
    @Path("/{idActuador}/historico")
    @Produces("application/json; charset=utf-8")
    public Response obtenerHistoricoActuador(@PathParam("idActuador") int idActuador, @QueryParam("fechaInicio") Date fechaInicio, @QueryParam("fechaFinal") Date fechaFinal) {
        Actuador actuador = administrador.obtenerActuador(idActuador);
        if (actuador == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
            );
        } else {
            List<HistoricoActuadores> historico = administrador.obtenerHistoricoActuador(idActuador, fechaInicio, fechaFinal);
            if (historico == null) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND).entity("Actuador no encontrado.").build()
                );
            }
            return Response.ok(historico).build();
        }
    }

    @GET
    @Path("/dependencia/{idDependencia}")
    @Produces("application/json; charset=utf-8")
    public ArrayList<Actuador> listarActuadores(@PathParam("idDependencia") int idDependencia) {
        return new ArrayList(clienteUso.listarActuadores(idDependencia).values());
    }

}
