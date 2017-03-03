package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.ChequeoService;
import cu.cenpis.gps.inv.data.entity.Chequeo;
import cu.cenpis.gps.inv.data.dao.ChequeoDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChequeoServiceImpl extends BaseServiceImpl<Chequeo, java.lang.Integer, ChequeoDAO>
        implements ChequeoService {

    public ChequeoServiceImpl() {
        System.out.println("ChequeoServiceImpl()");
    }
}

