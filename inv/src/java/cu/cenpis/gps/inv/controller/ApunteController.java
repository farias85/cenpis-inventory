package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class ApunteController extends BaseController<Apunte, java.lang.Integer> {

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }

    public ApunteController() {
        super(Apunte.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(apunteService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ApunteCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ApunteUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ApunteDeleted"));
    }
}

