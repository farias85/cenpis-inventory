package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class ActivoFijoController extends BaseController<ActivoFijo, java.lang.Long> {

    private ActivoFijoService activoFijoService;

    public ActivoFijoService getActivoFijoService() {
        return activoFijoService;
    }

    public void setActivoFijoService(ActivoFijoService activoFijoService) {
        this.activoFijoService = activoFijoService;
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
        int a1 = 10;
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ActivoFijoDeleted"));
    }
}

