package es.ujaen.iambiental.disparadorTarea;

import es.ujaen.iambiental.beans.AdminBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Raúl
 */
@Component
public class Tarea implements Job {

    @Autowired
    AdminBean administrador;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        int id = Integer.parseInt(context.getJobDetail().getKey().getName());
        System.out.println(administrador.obtenerTareaProgramada(id).getDescripcion());
        
        
        /*
        Aquí hay que comprobar todas las reglas que devuelve administrador.obtenerTareaProgramada(id)
        y ejecutar la que corresponda (lo dice el CRON)
        
        Modificar desde el AdminBean.modificarActuador(actuador)
        */
    }

}
