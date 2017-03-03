package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.InformeService;
import cu.cenpis.gps.inv.data.entity.Informe;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class InformeController extends BaseController<Informe, java.lang.Integer> {

    private InformeService informeService;

    public InformeService getInformeService() {
        return informeService;
    }

    public void setInformeService(InformeService informeService) {
        this.informeService = informeService;
    }

    public InformeController() {
        super(Informe.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(informeService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("InformeCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("InformeUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("InformeDeleted"));
    }
}

