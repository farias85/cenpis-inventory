package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.entity.Baja;
import cu.cenpis.gps.inv.data.service.BajaService;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class BajaController extends BaseController<Baja, java.lang.Integer> {

    private BajaService bajaService;

    public BajaService getBajaService() {
        return bajaService;
    }

    public void setBajaService(BajaService bajaService) {
        this.bajaService = bajaService;
    }
    
    public BajaController() {
        super(Baja.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(bajaService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("BajaCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("BajaUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("BajaDeleted"));
    }
}
