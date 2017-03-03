package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.InformeService;
import cu.cenpis.gps.inv.data.entity.Informe;
import cu.cenpis.gps.inv.data.dao.InformeDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InformeServiceImpl extends BaseServiceImpl<Informe, java.lang.Integer, InformeDAO>
        implements InformeService {

    public InformeServiceImpl() {
        System.out.println("InformeServiceImpl()");
    }
}

