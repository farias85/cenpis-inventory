package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.TipoActivoDAO;
import cu.cenpis.gps.inv.data.entity.TipoActivo;
import org.springframework.stereotype.Repository;

@Repository
public class TipoActivoDAOImpl extends AbstractHibernateDAO<TipoActivo, java.lang.Integer>
		implements TipoActivoDAO {

	public TipoActivoDAOImpl() {
		super(TipoActivo.class);
		System.out.println("TipoActivoDAOImpl");
	}

}

