package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.entity.Responsable;
import cu.cenpis.gps.inv.data.dao.ResponsableDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResponsableServiceImpl extends BaseServiceImpl<Responsable, java.lang.Long, ResponsableDAO>
        implements ResponsableService {

    public ResponsableServiceImpl() {
        System.out.println("ResponsableServiceImpl()");
    }
}

