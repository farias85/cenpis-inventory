/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.read;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vladimir
 */
public class Context {

    private final ApplicationContext context;

    private Context() {
        context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");
    }

    private static Context mContext;

    public static Object getBean(String bean) {
        if (mContext == null) {
            mContext = new Context();
        }
        return mContext.context.getBean(bean);
    }

//    public static void main(String[] args) {
//        UsuarioService usuarioService = (UsuarioService) Context.getBean("usuarioServiceImpl");
//    }
}
