package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Local;
import cu.cenpis.gps.inv.controller.LocalController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Local.class)
public class LocalConverter extends BaseConverter<Local, java.lang.Long, LocalController> {

    public LocalConverter() {
        super(Local.class, java.lang.Long.class);
    }

}

