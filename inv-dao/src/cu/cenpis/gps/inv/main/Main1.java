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
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.service.RevisionService;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.security.SecuredPassword;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author farias-i5
 */
public class Main1 {

    public static void main(String[] args) {
        // TODO code application logic here

        ApplicationContext context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");
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

//        ActivoFijo activoFijo = new ActivoFijo("inv-rotulo", "des", 1.5f, 1.0f, 2f, 2f, 2f, 2f, 2f, "resp", "estado", new Date(), new Date(), estado, local, responsable, revision);
//        activoFijoService.create(activoFijo);
//        List<Usuario> lu = usuarioService.findAll();
//        for (Usuario var : lu) {
//            try {
//                var.setContrasenna(SecuredPassword.generateStorngPasswordHash(var.getEmail()));
//            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
//                Logger.getLogger(Main1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            usuarioService.edit(var);
//        }
//        List<String> pList = new ArrayList<>();
//        pList.add("011621");
//        pList.add("011630");
//        List<ActivoFijo> aList = activoFijoService.findYaNoEstan();
        List<ActivoFijo> aList = activoFijoService.findNuevos();
        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i).getIdActivoFijo());
            System.out.println(aList.get(i).getRotulo());
        }
    }
}
