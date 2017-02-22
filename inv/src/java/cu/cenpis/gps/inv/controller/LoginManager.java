package cu.cenpis.gps.inv.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginManager implements Serializable {

    public String doLogin() {
        return doSpringRequest("/j_spring_security_check");
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        DestroyController destroyController = context.getApplication().evaluateExpressionGet(context, "#{destroyController}", DestroyController.class);
        destroyController.destroySession();
        
        return doSpringRequest("/j_spring_security_logout");
    }
    
    public String doSpringRequest(String url) {

        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(url);

            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

            FacesContext.getCurrentInstance().responseComplete();

        } catch (ServletException | IOException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

}
