package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Revision;
import cu.cenpis.gps.inv.controller.RevisionController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Revision.class)
public class RevisionConverter extends BaseConverter<Revision, java.lang.Long, RevisionController> {

    public RevisionConverter() {
        super(Revision.class, java.lang.Long.class);
    }

}

