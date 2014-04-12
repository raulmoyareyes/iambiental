package es.ujaen.iambiental.recursos;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.SensorErrorActualizar;
import es.ujaen.iambiental.excepciones.SensorErrorDatos;
import es.ujaen.iambiental.excepciones.SensorErrorEliminar;
import es.ujaen.iambiental.excepciones.SensorErrorPersistir;
import es.ujaen.iambiental.excepciones.SensorNoEncontrado;
import es.ujaen.iambiental.modelos.Sensor;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Recurso REST para el cliente de administración
 *
 * @author Gabri
 */
@Path("/sensores")
@Component(value = "recursoSensores")
public class RecursoSensores {
    
    @Autowired
    AdminBean administrador;
    
    @GET
    @Path("/{idSensor}")
    @Produces("application/json; charset=utf-8")
    public Response obtenerSensor(@PathParam("idSensor") String idSensor) {
        Sensor sensor = administrador.obtenerSensor(idSensor);
        if (sensor == null) {
            throw new WebApplicationException(
                    Response.status(Status.NOT_FOUND).entity("Sensor no encontrado.").build()
            );
        }
        return Response.ok(sensor).build();

    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public Response listarSensores() {
        return Response.ok(administrador.listarSensores()).build();
    }
    
    @PUT
    @Path("/{idSensor}")
    @Consumes("application/json")
    public Response crearSensor(@PathParam("idSensor") String idSensor, Sensor sensor) {
        if (sensor == null) {
            throw new WebApplicationException(
                    Response.status(Status.BAD_REQUEST).entity("Falta el objeto sensor.").build()
            );
        }

        if (administrador.obtenerSensor(idSensor) != null) {
            throw new WebApplicationException(
                    Response.status(Status.CONFLICT).entity("Sensor existente.").build()
            );
        }
        try {
            administrador.crearSensor(sensor);
        } catch (SensorErrorDatos | SensorErrorPersistir e) {
            throw new WebApplicationException(
                    Response.status(Status.NOT_ACCEPTABLE).entity("Error al crear el sensor.").build()
            );
        }
        return Response.status(Status.ACCEPTED).build();
    }
    
    @DELETE
    @Path("/{idSensor}")
    //@Consumes("application/json")
    public Response eliminarSensor(@PathParam("idSensor") String idSensor) {
        Sensor sensor = administrador.obtenerSensor(idSensor);
        if (sensor == null) {
            throw new WebApplicationException(
                    Response.status(Status.NOT_FOUND).entity("Sensor no encontrado.").build()
            );
        } else {
            try {
                administrador.eliminarSensor(idSensor);
            } catch (SensorErrorEliminar | SensorNoEncontrado e) {
                throw new WebApplicationException(
                        Response.status(Status.NOT_ACCEPTABLE).entity("Error al eliminar el sensor.").build()
                );
            }
            return Response.status(Status.ACCEPTED).build();
        }
    }
    
    @Path("/{idSensor}")
    @Consumes("application/json")
    public Response modificarSensor(@PathParam("idSensor") String idSensor, Sensor sensor) {
        if (sensor == null) {
            throw new WebApplicationException(
                    Response.status(Status.BAD_REQUEST).entity("Falta el objeto sensor.").build()
            );
        }

        if (administrador.obtenerSensor(idSensor) == null) {
            throw new WebApplicationException(
                    Response.status(Status.NOT_FOUND).entity("Sensor no encontrado.").build()
            );
        }
        try {
            administrador.modificarSensor(sensor);
        } catch (SensorErrorActualizar e) {
            throw new WebApplicationException(
                    Response.status(Status.NOT_ACCEPTABLE).entity("Error al actualizar el sensor.").build()
            );
        }
        return Response.status(Status.ACCEPTED).build();
    }
    
}
