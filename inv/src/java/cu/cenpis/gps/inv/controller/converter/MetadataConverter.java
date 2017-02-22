package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Metadata;
import cu.cenpis.gps.inv.controller.MetadataController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Metadata.class)
public class MetadataConverter extends BaseConverter<Metadata, java.lang.Long, MetadataController> {

    public MetadataConverter() {
        super(Metadata.class, java.lang.Long.class);
    }

}

