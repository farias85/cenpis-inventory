package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.TipoActivoService;
import cu.cenpis.gps.inv.data.entity.TipoActivo;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class TipoActivoController extends BaseController<TipoActivo, java.lang.Integer> {

    private TipoActivoService tipoActivoService;

    public TipoActivoService getTipoActivoService() {
        return tipoActivoService;
    }

    public void setTipoActivoService(TipoActivoService tipoActivoService) {
        this.tipoActivoService = tipoActivoService;
    }

    public TipoActivoController() {
        super(TipoActivo.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(tipoActivoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("TipoActivoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("TipoActivoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("TipoActivoDeleted"));
    }
}

