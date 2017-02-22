package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.ResponsableDAO;
import cu.cenpis.gps.inv.data.entity.Responsable;
import org.springframework.stereotype.Repository;

@Repository
public class ResponsableDAOImpl extends AbstractHibernateDAO<Responsable, java.lang.Long>
		implements ResponsableDAO {

	public ResponsableDAOImpl() {
		super(Responsable.class);
		System.out.println("ResponsableDAOImpl");
	}

}

