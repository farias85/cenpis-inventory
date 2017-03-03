package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.TipoResultado;
import cu.cenpis.gps.inv.controller.TipoResultadoController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = TipoResultado.class)
public class TipoResultadoConverter extends BaseConverter<TipoResultado, java.lang.Integer, TipoResultadoController> {

    public TipoResultadoConverter() {
        super(TipoResultado.class, java.lang.Integer.class);
    }

}

