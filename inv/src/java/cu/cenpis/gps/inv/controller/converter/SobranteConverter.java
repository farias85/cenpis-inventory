package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.controller.SobranteController;
import cu.cenpis.gps.inv.data.entity.Sobrante;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Sobrante.class)
public class SobranteConverter extends BaseConverter<Sobrante, java.lang.Long, SobranteController> {

    public SobranteConverter() {
        super(Sobrante.class, java.lang.Long.class);
    }
}
