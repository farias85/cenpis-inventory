/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.main;

import cu.cenpis.gps.inv.data.service.UsuarioService;
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
        UsuarioService usuarioService = (UsuarioService)context.getBean("usuarioServiceImpl");

    }
}
