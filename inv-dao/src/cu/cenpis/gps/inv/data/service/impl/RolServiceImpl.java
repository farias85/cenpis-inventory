package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.RolService;
import cu.cenpis.gps.inv.data.entity.Rol;
import cu.cenpis.gps.inv.data.dao.RolDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl extends BaseServiceImpl<Rol, java.lang.Long, RolDAO>
        implements RolService {

    public RolServiceImpl() {
        System.out.println("RolServiceImpl()");
    }
}

