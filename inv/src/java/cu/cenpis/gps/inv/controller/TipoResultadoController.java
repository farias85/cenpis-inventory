package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.TipoResultadoService;
import cu.cenpis.gps.inv.data.entity.TipoResultado;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class TipoResultadoController extends BaseController<TipoResultado, java.lang.Integer> {

    private TipoResultadoService tipoResultadoService;

    public TipoResultadoService getTipoResultadoService() {
        return tipoResultadoService;
    }

    public void setTipoResultadoService(TipoResultadoService tipoResultadoService) {
        this.tipoResultadoService = tipoResultadoService;
    }

    public TipoResultadoController() {
        super(TipoResultado.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(tipoResultadoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("TipoResultadoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("TipoResultadoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("TipoResultadoDeleted"));
    }
}

