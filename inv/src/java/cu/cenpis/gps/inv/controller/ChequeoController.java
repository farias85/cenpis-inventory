package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ChequeoService;
import cu.cenpis.gps.inv.data.entity.Chequeo;
import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class ChequeoController extends BaseController<Chequeo, java.lang.Integer> {

    private ChequeoService chequeoService;

    public ChequeoService getChequeoService() {
        return chequeoService;
    }

    public void setChequeoService(ChequeoService chequeoService) {
        this.chequeoService = chequeoService;
    }

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }
    
    public ChequeoController() {
        super(Chequeo.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(chequeoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ChequeoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ChequeoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ChequeoDeleted"));
    }    
    
    @Override
    public void setEmbeddableKeys(){
        apunteService.edit(selected.getApunte());
    }
}

