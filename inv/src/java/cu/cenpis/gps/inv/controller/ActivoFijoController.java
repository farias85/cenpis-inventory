package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.util.Bundle;
import java.util.List;
import javax.annotation.PostConstruct;

public class ActivoFijoController extends BaseController<ActivoFijo, java.lang.Long> {

    private ActivoFijoService activoFijoService;

    public ActivoFijoService getActivoFijoService() {
        return activoFijoService;
    }

    public void setActivoFijoService(ActivoFijoService activoFijoService) {
        this.activoFijoService = activoFijoService;
    }

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }

    public ActivoFijoController() {
        super(ActivoFijo.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(activoFijoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ActivoFijoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ActivoFijoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ActivoFijoDeleted"));
    }

    @Override
    public List<ActivoFijo> getItems() {
        if (items == null) {
            items = getFacade().findNamedQuery("ActivoFijo.findByRevisionActiva");
        }
        return items;
    }

    public List<ActivoFijo> getOtrosActivos() {
        return getFacade().findNamedQuery("ActivoFijo.findByRotulo", "rotulo", selected.getRotulo());
    }

    public List<Apunte> getActivoApuntes() {
        return apunteService.findNamedQuery("Apunte.findByRotulo", "rotulo", selected.getRotulo());
    }

    public List<ActivoFijo> getActivosNuevos() {
        return getFacade().findNamedQuery("ActivoFijo.findByRotulo", "rotulo", "011620");
    }
    
    public List<ActivoFijo> getActivosYaNoEstan() {
        return getFacade().findNamedQuery("ActivoFijo.findByRotulo", "rotulo", "011620");
    }
}
