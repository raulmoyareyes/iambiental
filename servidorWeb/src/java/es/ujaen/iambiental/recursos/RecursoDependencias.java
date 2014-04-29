package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.DependenciaErrorActualizar;
import es.ujaen.iambiental.excepciones.DependenciaErrorDatos;
import es.ujaen.iambiental.excepciones.DependenciaErrorEliminar;
import es.ujaen.iambiental.excepciones.DependenciaErrorPersistir;
import es.ujaen.iambiental.excepciones.DependenciaNoEncontrada;
import es.ujaen.iambiental.modelos.Dependencia;
import java.util.ArrayList;
import java.util.List;
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
@Path("/dependencias")
@Component(value = "recursoDependencias")
public class RecursoDependencias {

    @Autowired
    AdminBean administrador;

    @GET
    @Path("/{idDependencia}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerDependencia(@PathParam("idDependencia") Integer idDependencia) {
        Dependencia dependencia = administrador.obtenerDependencia(idDependencia);
        if (dependencia == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dependencia no encontrada.").build()
            );
        }
        return Response.ok(dependencia).build();

    }

    @GET
    @Produces("application/json; charset=utf-8")
    public List<Dependencia> listarDependencias() {
        return new ArrayList(administrador.listarDependencias().values());
    }

    @PUT
    @Consumes("application/json")
    public Response crearDependencia(Dependencia dependencia) {
        if (dependencia == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto dependencia.").build()
            );
        }

        try {
            administrador.crearDependencia(dependencia);
        } catch (DependenciaErrorDatos | DependenciaErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al crear la dependencia.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("/{idDependencia}")
    //@Consumes("application/json")
    public Response eliminarDependencia(@PathParam("idDependencia") Integer idDependencia) {
        Dependencia dependencia = administrador.obtenerDependencia(idDependencia);
        if (dependencia == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dependencia no encontrada.").build()
            );
        } else {
            try {
                administrador.eliminarDependencia(idDependencia);
            } catch (DependenciaErrorEliminar | DependenciaNoEncontrada e) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al eliminar la dependencia.").build()
                );
            }
            return Response.status(Response.Status.ACCEPTED).build();
        }
    }

    @POST
    @Path("/{idDependencia}")
    @Consumes("application/json")
    public Response modificarDependencia(@PathParam("idDependencia") Integer idDependencia, Dependencia dependencia) {
        if (dependencia == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Falta el objeto dependencia.").build()
            );
        }

        if (administrador.obtenerDependencia(idDependencia) == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Dependencia no encontrada.").build()
            );
        }
        try {
            administrador.modificarDependencia(dependencia);
        } catch (DependenciaErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error al actualizar la dependencia.").build()
            );
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

}
