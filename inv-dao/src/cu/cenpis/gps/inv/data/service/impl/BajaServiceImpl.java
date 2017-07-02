package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.dao.BajaDAO;
import cu.cenpis.gps.inv.data.entity.Baja;
import cu.cenpis.gps.inv.data.service.BajaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BajaServiceImpl extends BaseServiceImpl<Baja, java.lang.Integer, BajaDAO>
        implements BajaService {

    public BajaServiceImpl() {
        System.out.println("BajaServiceImpl()");
    }
}
