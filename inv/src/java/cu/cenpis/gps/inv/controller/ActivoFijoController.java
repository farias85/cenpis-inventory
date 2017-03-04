package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class ActivoFijoController extends BaseController<ActivoFijo, java.lang.Long> {

    private ActivoFijoService activoFijoService;

    public ActivoFijoService getActivoFijoService() {
        return activoFijoService;
    }

    public void setActivoFijoService(ActivoFijoService activoFijoService) {
        this.activoFijoService = activoFijoService;
    }

    private ApunteService apunteService;

    public ApunteService getApunteService() {
        return apunteService;
    }

    public void setApunteService(ApunteService apunteService) {
        this.apunteService = apunteService;
    }

    public ActivoFijoController() {
        super(ActivoFijo.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(activoFijoService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("ActivoFijoCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("ActivoFijoUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("ActivoFijoDeleted"));
    }

    @Override
    public List<ActivoFijo> getItems() {
        if (items == null) {
            items = getFacade().findNamedQuery("ActivoFijo.findByRevisionActiva");
        }
        return items;
    }

    public List<ActivoFijo> getOtrosActivos() {
        return getFacade().findNamedQuery("ActivoFijo.findByRotulo", "rotulo", selected.getRotulo());
    }

    public List<Apunte> getActivoApuntes() {
        return apunteService.findNamedQuery("Apunte.findByRotulo", "rotulo", selected.getRotulo());
    }

    public List<ActivoFijo> getActivosNuevos() {
        return getItems();
        //return activoFijoService.findNuevos();        
    }

    public List<ActivoFijo> getActivosYaNoEstan() {
        return activoFijoService.findYaNoEstan();
    }

    private List<ActivoFijo> selection = new ArrayList<>();

    public List<ActivoFijo> getSelection() {
        return selection;
    }

    public void setSelection(List<ActivoFijo> selection) {
        this.selection = selection;
    }

    public int getSelectionSize() {
        return selection.size();
    }

    public String actionInformePrepareCreate() {
        InformeController informeController = JsfUtil.getController(InformeController.class);
        informeController.prepareCreate();
        return "navInformeCreate";
    }
}
