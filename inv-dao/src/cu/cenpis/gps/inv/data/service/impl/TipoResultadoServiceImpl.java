package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.TipoResultadoService;
import cu.cenpis.gps.inv.data.entity.TipoResultado;
import cu.cenpis.gps.inv.data.dao.TipoResultadoDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoResultadoServiceImpl extends BaseServiceImpl<TipoResultado, java.lang.Integer, TipoResultadoDAO>
        implements TipoResultadoService {

    public TipoResultadoServiceImpl() {
        System.out.println("TipoResultadoServiceImpl()");
    }
}

