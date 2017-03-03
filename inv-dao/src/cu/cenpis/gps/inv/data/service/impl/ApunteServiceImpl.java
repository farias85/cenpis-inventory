package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.ApunteService;
import cu.cenpis.gps.inv.data.entity.Apunte;
import cu.cenpis.gps.inv.data.dao.ApunteDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApunteServiceImpl extends BaseServiceImpl<Apunte, java.lang.Integer, ApunteDAO>
        implements ApunteService {

    public ApunteServiceImpl() {
        System.out.println("ApunteServiceImpl()");
    }
}

