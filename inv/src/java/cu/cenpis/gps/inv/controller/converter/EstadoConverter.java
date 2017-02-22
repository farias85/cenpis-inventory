package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Estado;
import cu.cenpis.gps.inv.controller.EstadoController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter extends BaseConverter<Estado, java.lang.Long, EstadoController> {

    public EstadoConverter() {
        super(Estado.class, java.lang.Long.class);
    }

}

