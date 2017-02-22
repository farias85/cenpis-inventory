package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.entity.Estado;
import cu.cenpis.gps.inv.data.dao.EstadoDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadoServiceImpl extends BaseServiceImpl<Estado, java.lang.Long, EstadoDAO>
        implements EstadoService {

    public EstadoServiceImpl() {
        System.out.println("EstadoServiceImpl()");
    }
}

