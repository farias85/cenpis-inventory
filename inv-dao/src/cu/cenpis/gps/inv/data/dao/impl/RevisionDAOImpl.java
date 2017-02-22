package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.RevisionDAO;
import cu.cenpis.gps.inv.data.entity.Revision;
import org.springframework.stereotype.Repository;

@Repository
public class RevisionDAOImpl extends AbstractHibernateDAO<Revision, java.lang.Long>
		implements RevisionDAO {

	public RevisionDAOImpl() {
		super(Revision.class);
		System.out.println("RevisionDAOImpl");
	}

}

