package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.data.service.RevisionService;
import cu.cenpis.gps.inv.data.entity.Revision;
import cu.cenpis.gps.inv.util.Bundle;
import javax.annotation.PostConstruct;

public class RevisionController extends BaseController<Revision, java.lang.Long> {

    private RevisionService revisionService;

    public RevisionService getRevisionService() {
        return revisionService;
    }

    public void setRevisionService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public RevisionController() {
        super(Revision.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(revisionService);
    }

    @Override
    public void create() {
        super.create(Bundle.getString("RevisionCreated"));
    }

    @Override
    public void update() {
        super.update(Bundle.getString("RevisionUpdated"));
    }

    @Override
    public void destroy() {
        super.destroy(Bundle.getString("RevisionDeleted"));
    }
}

