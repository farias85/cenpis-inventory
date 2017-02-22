package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.AuditoriaService;
import cu.cenpis.gps.inv.data.entity.Auditoria;
import cu.cenpis.gps.inv.data.dao.AuditoriaDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditoriaServiceImpl extends BaseServiceImpl<Auditoria, java.lang.Long, AuditoriaDAO>
        implements AuditoriaService {

    public AuditoriaServiceImpl() {
        System.out.println("AuditoriaServiceImpl()");
    }
}

