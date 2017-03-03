package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Chequeo;
import cu.cenpis.gps.inv.controller.ChequeoController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Chequeo.class)
public class ChequeoConverter extends BaseConverter<Chequeo, java.lang.Integer, ChequeoController> {

    public ChequeoConverter() {
        super(Chequeo.class, java.lang.Integer.class);
    }

}

