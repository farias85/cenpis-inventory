package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Informe;
import cu.cenpis.gps.inv.controller.InformeController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Informe.class)
public class InformeConverter extends BaseConverter<Informe, java.lang.Integer, InformeController> {

    public InformeConverter() {
        super(Informe.class, java.lang.Integer.class);
    }

}

