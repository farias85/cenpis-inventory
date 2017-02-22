package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.entity.Estado;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class EstadoController extends BaseController<Estado, java.lang.Long> {

    private EstadoService estadoService;

    public EstadoService getEstadoService() {
        return estadoService;
    }

    public void setEstadoService(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public EstadoController() {
        super(Estado.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(estadoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("EstadoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("EstadoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("EstadoDeleted"));
    }
}

