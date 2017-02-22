package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Responsable;
import cu.cenpis.gps.inv.controller.ResponsableController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Responsable.class)
public class ResponsableConverter extends BaseConverter<Responsable, java.lang.Long, ResponsableController> {

    public ResponsableConverter() {
        super(Responsable.class, java.lang.Long.class);
    }

}

