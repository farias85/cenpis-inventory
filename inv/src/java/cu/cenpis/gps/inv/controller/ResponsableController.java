package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.entity.Responsable;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class ResponsableController extends BaseController<Responsable, java.lang.Long> {

    private ResponsableService responsableService;

    public ResponsableService getResponsableService() {
        return responsableService;
    }

    public void setResponsableService(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    public ResponsableController() {
        super(Responsable.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(responsableService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ResponsableCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ResponsableUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ResponsableDeleted"));
    }
}

