package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.EstadoDAO;
import cu.cenpis.gps.inv.data.entity.Estado;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImpl extends AbstractHibernateDAO<Estado, java.lang.Long>
		implements EstadoDAO {

	public EstadoDAOImpl() {
		super(Estado.class);
		System.out.println("EstadoDAOImpl");
	}

}

