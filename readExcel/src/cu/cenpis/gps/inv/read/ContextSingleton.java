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
public class ContextSingleton {

    private ApplicationContext context;

    private ContextSingleton() {
        context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");
    }

    private static ContextSingleton mContext;

    public static ApplicationContext getInstance() {
        if (mContext == null) {
            mContext = new ContextSingleton();        
        }
        return mContext.context;
    }
}
