/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.main;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Estado;
import cu.cenpis.gps.inv.data.entity.Local;
import cu.cenpis.gps.inv.data.entity.Responsable;
import cu.cenpis.gps.inv.data.entity.Revision;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.service.RevisionService;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author farias-i5
 */
public class Main1 {

    public static void main(String[] args) {
        // TODO code application logic here

       /* ApplicationContext context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");
        UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioServiceImpl");
        ActivoFijoService activoFijoService = (ActivoFijoService) context.getBean("activoFijoServiceImpl");
        EstadoService estadoService = (EstadoService) context.getBean("estadoServiceImpl");
        LocalService localService = (LocalService) context.getBean("localServiceImpl");
        ResponsableService responsableService = (ResponsableService) context.getBean("responsableServiceImpl");
        RevisionService revisionService = (RevisionService) context.getBean("revisionServiceImpl");

        Estado estado = estadoService.find(0L);
        Local local = localService.find(0L);
        Responsable responsable = responsableService.find(0L);
        Revision revision = revisionService.find(0L);

        ActivoFijo activoFijo = new ActivoFijo("inv-rotulo", "des", 1.5, 1.0, 2, 2, 2, 2, 2, "resp", "estado", new Date(), new Date(), estado, local, responsable, revision);
        activoFijoService.create(activoFijo);
        
        Revision rev = new Revision();
        rev.setActivo(Boolean.TRUE);
        revisionService.findByExample(rev);*/
    }
}
