package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.util.Date;
import javax.annotation.PostConstruct;

public class ApunteController extends BaseController<Apunte, java.lang.Integer> {

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }

    private UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ApunteController() {
        super(Apunte.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(apunteService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ApunteCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ApunteUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ApunteDeleted"));
    }

    @Override
    protected void setEmbeddableKeys() {
        super.setEmbeddableKeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void initializeEmbeddableKey() {
        if (selected.getUsuario() == null) {
            UsuarioController usuarioController = JsfUtil.getController(UsuarioController.class);
            if (usuarioController.getActiveUser() != null) {
                selected.setUsuario(usuarioController.getActiveUser());
            } else {
                selected.setUsuario(usuarioService.find(1L));
            }
        }
        if (selected.getFecha() == null) {
            selected.setFecha(new Date());
        }
        if (selected.getRotulo() == null || selected.getRotulo().isEmpty()) {
            ActivoFijoController activoFijoController = JsfUtil.getController(ActivoFijoController.class);
            selected.setRotulo(activoFijoController.getSelected().getRotulo());
        }
    }

}
