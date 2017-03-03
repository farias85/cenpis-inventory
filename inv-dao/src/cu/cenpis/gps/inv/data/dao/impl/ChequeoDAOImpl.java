package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.ChequeoDAO;
import cu.cenpis.gps.inv.data.entity.Chequeo;
import org.springframework.stereotype.Repository;

@Repository
public class ChequeoDAOImpl extends AbstractHibernateDAO<Chequeo, java.lang.Integer>
		implements ChequeoDAO {

	public ChequeoDAOImpl() {
		super(Chequeo.class);
		System.out.println("ChequeoDAOImpl");
	}

}

