package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.entity.Local;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class LocalController extends BaseController<Local, java.lang.Long> {

    private LocalService localService;

    public LocalService getLocalService() {
        return localService;
    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }

    public LocalController() {
        super(Local.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(localService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("LocalCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("LocalUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("LocalDeleted"));
    }
}

