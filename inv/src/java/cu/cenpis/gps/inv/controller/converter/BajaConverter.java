package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.controller.BajaController;
import cu.cenpis.gps.inv.data.entity.Baja;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Baja.class)
public class BajaConverter extends BaseConverter<Baja, java.lang.Integer, BajaController> {

    public BajaConverter() {
        super(Baja.class, java.lang.Integer.class);
    }
}
