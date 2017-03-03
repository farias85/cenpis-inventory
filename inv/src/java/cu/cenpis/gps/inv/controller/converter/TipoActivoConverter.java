package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.TipoActivo;
import cu.cenpis.gps.inv.controller.TipoActivoController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = TipoActivo.class)
public class TipoActivoConverter extends BaseConverter<TipoActivo, java.lang.Integer, TipoActivoController> {

    public TipoActivoConverter() {
        super(TipoActivo.class, java.lang.Integer.class);
    }

}

