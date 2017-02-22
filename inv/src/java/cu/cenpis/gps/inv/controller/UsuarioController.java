package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class UsuarioController extends BaseController<Usuario, java.lang.Long> {

    private UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(usuarioService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("UsuarioCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("UsuarioUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("UsuarioDeleted"));
    }
}

