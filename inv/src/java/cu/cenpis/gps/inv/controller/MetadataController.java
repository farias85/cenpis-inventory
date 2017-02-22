package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.MetadataService;
import cu.cenpis.gps.inv.data.entity.Metadata;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class MetadataController extends BaseController<Metadata, java.lang.Long> {

    private MetadataService metadataService;

    public MetadataService getMetadataService() {
        return metadataService;
    }

    public void setMetadataService(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    public MetadataController() {
        super(Metadata.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(metadataService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("MetadataCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("MetadataUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("MetadataDeleted"));
    }
}

