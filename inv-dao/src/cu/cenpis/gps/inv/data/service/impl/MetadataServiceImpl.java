package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.MetadataService;
import cu.cenpis.gps.inv.data.entity.Metadata;
import cu.cenpis.gps.inv.data.dao.MetadataDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MetadataServiceImpl extends BaseServiceImpl<Metadata, java.lang.Long, MetadataDAO>
        implements MetadataService {

    public MetadataServiceImpl() {
        System.out.println("MetadataServiceImpl()");
    }
}

