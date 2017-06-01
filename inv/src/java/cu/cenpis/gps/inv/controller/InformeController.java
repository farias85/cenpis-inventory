package cu.cenpis.gps.inv.controller;

import com.sun.faces.facelets.util.Path;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.entity.Chequeo;
import cu.cenpis.gps.inv.data.service.InformeService;
import cu.cenpis.gps.inv.data.entity.Informe;
import cu.cenpis.gps.inv.data.entity.TipoResultado;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.data.service.ChequeoService;
import cu.cenpis.gps.inv.data.service.TipoResultadoService;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.data.util.HibernateUtil;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;

public class InformeController extends BaseController<Informe, java.lang.Integer> {

    private InformeService informeService;

    public InformeService getInformeService() {
        return informeService;
    }

    public void setInformeService(InformeService informeService) {
        this.informeService = informeService;
    }

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
    
    private UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
     
    private TipoResultadoService tipoResultadoService;

    public TipoResultadoService getTipoResultadoService() {
        return tipoResultadoService;
    }

    public void setTipoResultadoService(TipoResultadoService tipoResultadoService) {
        this.tipoResultadoService = tipoResultadoService;
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
        if (Objects.nonNull(selected.getIdInforme())) {
            //crear un apunte y un chequeo por cada rotulo

            //Tipo de resultado EN_REVISION  
            TipoResultado tipoResultado = tipoResultadoService.find(0);                        
            Usuario usuario = usuarioService.find(1L);
            ActivoFijoController activoFijoController = JsfUtil.getController(ActivoFijoController.class);
            
            for (ActivoFijo selection : activoFijoController.getSelection()) {
                Apunte apunte =  new Apunte(selection.getRotulo(), new Date() , selection.getDescripcion(), " ", usuario/*usuarioController.getActiveUser()*/);
                apunteService.create(apunte);
                Chequeo chequeo = new Chequeo(selected, apunte, tipoResultado);
                chequeoService.create(chequeo);
            }
        }
    }

    @Override
    public void update() {
        super.update(Bundle.getString("InformeUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("InformeDeleted"));
    }

    @Override
    public void setEmbeddableKeys() {
        if (Objects.isNull(selected.getCompletado())) {
            selected.setCompletado(Boolean.FALSE);
        }
    }
    
    public List<Chequeo> getChequeosInforme() {      
        
        List<Chequeo> listaChequeo = chequeoService.findNamedQuery("Chequeo.findByIdInforme", "idInforme", selected.getIdInforme());       
        return listaChequeo;
    }
}
