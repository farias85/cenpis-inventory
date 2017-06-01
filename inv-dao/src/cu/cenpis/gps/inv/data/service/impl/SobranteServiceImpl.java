package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.dao.SobranteDAO;
import cu.cenpis.gps.inv.data.entity.Sobrante;
import cu.cenpis.gps.inv.data.service.SobranteService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SobranteServiceImpl extends BaseServiceImpl<Sobrante, java.lang.Long, SobranteDAO>
        implements SobranteService {

    public SobranteServiceImpl() {
        System.out.println("SobranteServiceImpl()");
    }
}
