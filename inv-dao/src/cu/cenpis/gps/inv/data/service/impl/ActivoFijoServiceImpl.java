package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.dao.ActivoFijoDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivoFijoServiceImpl extends BaseServiceImpl<ActivoFijo, java.lang.Long, ActivoFijoDAO>
        implements ActivoFijoService {

    public ActivoFijoServiceImpl() {
        System.out.println("ActivoFijoServiceImpl()");
    }
}

