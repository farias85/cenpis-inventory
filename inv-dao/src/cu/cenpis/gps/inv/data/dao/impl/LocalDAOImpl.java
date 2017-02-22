package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.LocalDAO;
import cu.cenpis.gps.inv.data.entity.Local;
import org.springframework.stereotype.Repository;

@Repository
public class LocalDAOImpl extends AbstractHibernateDAO<Local, java.lang.Long>
		implements LocalDAO {

	public LocalDAOImpl() {
		super(Local.class);
		System.out.println("LocalDAOImpl");
	}

}

