package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Auditoria;
import cu.cenpis.gps.inv.controller.AuditoriaController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Auditoria.class)
public class AuditoriaConverter extends BaseConverter<Auditoria, java.lang.Long, AuditoriaController> {

    public AuditoriaConverter() {
        super(Auditoria.class, java.lang.Long.class);
    }

}

