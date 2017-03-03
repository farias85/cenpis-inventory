package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.TipoActivoService;
import cu.cenpis.gps.inv.data.entity.TipoActivo;
import cu.cenpis.gps.inv.data.dao.TipoActivoDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoActivoServiceImpl extends BaseServiceImpl<TipoActivo, java.lang.Integer, TipoActivoDAO>
        implements TipoActivoService {

    public TipoActivoServiceImpl() {
        System.out.println("TipoActivoServiceImpl()");
    }
}

