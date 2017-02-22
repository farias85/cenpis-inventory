package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.entity.Local;
import cu.cenpis.gps.inv.data.dao.LocalDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocalServiceImpl extends BaseServiceImpl<Local, java.lang.Long, LocalDAO>
        implements LocalService {

    public LocalServiceImpl() {
        System.out.println("LocalServiceImpl()");
    }
}

