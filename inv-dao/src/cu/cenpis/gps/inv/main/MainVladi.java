/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.main;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.RevisionService;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vladimir
 */
public class MainVladi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");        
        RevisionService revisionService = (RevisionService) context.getBean("revisionServiceImpl");
        ActivoFijoService activoFijoService = (ActivoFijoService) context.getBean("activoFijoServiceImpl");

//        HashMap<String, Object> params = new HashMap<>();
//        params.put("mRotulo", "011621");
//        params.put("idRevision", 5L);
//        List<ActivoFijo> activosFijos = activoFijoService.findNamedQuery("ActivoFijo.findRevision", params);
//
//        for (ActivoFijo activosFijo : activosFijos) {
//            System.out.println(activosFijo.getIdActivoFijo());
//        }
        
        
    }
}
