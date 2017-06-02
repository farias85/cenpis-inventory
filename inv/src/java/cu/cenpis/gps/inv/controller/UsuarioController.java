package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.entity.Rol;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.security.SecuredPassword;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

public class UsuarioController extends BaseController<Usuario, java.lang.Long> {

    private UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    private Usuario activeUser;

    public Usuario getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Usuario activeUser) {
        this.activeUser = activeUser;
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
        if (validarExiste() != (null)) {
            super.create(Bundle.getString("UsuarioCreated"));
        }
    }

    @Override
    public void update() {
        if (validarExiste() != (null)) {
            super.update(Bundle.getString("UsuarioUpdated"));
        } else {
            usuarioService.refrescarSelected(selected);
        }
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("UsuarioDeleted"));
    }

    @Override
    protected void setEmbeddableKeys() {
        RolController rolController = JsfUtil.getController(RolController.class);
        selected.setRolList(new HashSet<>(rolController.getFiltered()));
        try {
            selected.setContrasenna(SecuredPassword.generateStorngPasswordHash(selected.getContrasenna()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setEmbeddableKeys(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void initializeEmbeddableKey() {
        RolController rolController = JsfUtil.getController(RolController.class);
        rolController.setFiltered(new ArrayList<>());
        super.initializeEmbeddableKey(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getEditRendered() {
        if (selected == null) {
            return false;
        }
        RolController rolController = JsfUtil.getController(RolController.class);
        List<Rol> rl = usuarioService.getRolList(selected);
        rolController.setFiltered(rl);
        return true;
    }

    private String validarExiste() {
        if (usuarioService.existe(selected)) {
            JsfUtil.addErrorMessage(Bundle.getString("ExisteUsuario"));
            return null;
        }
        return " ";
    }
}

