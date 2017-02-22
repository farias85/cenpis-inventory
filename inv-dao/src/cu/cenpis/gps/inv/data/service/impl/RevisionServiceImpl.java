package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.RevisionService;
import cu.cenpis.gps.inv.data.entity.Revision;
import cu.cenpis.gps.inv.data.dao.RevisionDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RevisionServiceImpl extends BaseServiceImpl<Revision, java.lang.Long, RevisionDAO>
        implements RevisionService {

    public RevisionServiceImpl() {
        System.out.println("RevisionServiceImpl()");
    }
}

