package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.AuditoriaService;
import cu.cenpis.gps.inv.data.entity.Auditoria;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class AuditoriaController extends BaseController<Auditoria, java.lang.Long> {

    private AuditoriaService auditoriaService;

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    public AuditoriaController() {
        super(Auditoria.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(auditoriaService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("AuditoriaCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("AuditoriaUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("AuditoriaDeleted"));
    }
}

