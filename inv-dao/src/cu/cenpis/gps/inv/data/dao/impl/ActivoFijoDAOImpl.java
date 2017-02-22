package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.ActivoFijoDAO;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import org.springframework.stereotype.Repository;

@Repository
public class ActivoFijoDAOImpl extends AbstractHibernateDAO<ActivoFijo, java.lang.Long>
		implements ActivoFijoDAO {

	public ActivoFijoDAOImpl() {
		super(ActivoFijo.class);
		System.out.println("ActivoFijoDAOImpl");
	}

}

