package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.MetadataDAO;
import cu.cenpis.gps.inv.data.entity.Metadata;
import org.springframework.stereotype.Repository;

@Repository
public class MetadataDAOImpl extends AbstractHibernateDAO<Metadata, java.lang.Long>
		implements MetadataDAO {

	public MetadataDAOImpl() {
		super(Metadata.class);
		System.out.println("MetadataDAOImpl");
	}

}

