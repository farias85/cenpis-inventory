package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.entity.Sobrante;
import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.data.service.SobranteService;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class SobranteController extends BaseController<Sobrante, java.lang.Long> {

    private SobranteService sobranteService;

    public SobranteService getSobranteService() {
        return sobranteService;
    }

    public void setSobranteService(SobranteService sobranteService) {
        this.sobranteService = sobranteService;
    }

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }

    public SobranteController() {
        super(Sobrante.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(sobranteService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("SobranteCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("SobranteUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("SobranteDeleted"));
    }
}
