/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.util.JsfUtil;
import java.io.Serializable;

/**
 *
 * @author farias-i5
 */
public class DestroyController implements Serializable {

    public void destroySession() {
        
        destroyVars(ActivoFijoController.class);
        destroyVars(ApunteController.class);
        destroyVars(AuditoriaController.class);
        destroyVars(ChequeoController.class);
        destroyVars(EstadoController.class);
        destroyVars(InformeController.class);
        destroyVars(LocalController.class);
        
        destroyVars(MetadataController.class);
        destroyVars(ResponsableController.class);
        destroyVars(RevisionController.class);
        destroyVars(TipoActivoController.class);
        destroyVars(TipoResultadoController.class);
        
        destroyVars(RolController.class);        
        destroyVars(UsuarioController.class);
        
        UsuarioController uc = JsfUtil.getController(UsuarioController.class);
        uc.setActiveUser(null);
    }

    private void destroyVars(Class<? extends BaseController> type) {
        BaseController controller = JsfUtil.getController(type);
        controller.setItems(null);
        controller.setFiltered(null);
    }
}
