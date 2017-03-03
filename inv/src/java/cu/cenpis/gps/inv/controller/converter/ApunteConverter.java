package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.controller.ApunteController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Apunte.class)
public class ApunteConverter extends BaseConverter<Apunte, java.lang.Integer, ApunteController> {

    public ApunteConverter() {
        super(Apunte.class, java.lang.Integer.class);
    }

}

