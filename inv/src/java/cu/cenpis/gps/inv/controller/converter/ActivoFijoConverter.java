package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.controller.ActivoFijoController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ActivoFijo.class)
public class ActivoFijoConverter extends BaseConverter<ActivoFijo, java.lang.Long, ActivoFijoController> {

    public ActivoFijoConverter() {
        super(ActivoFijo.class, java.lang.Long.class);
    }

}

