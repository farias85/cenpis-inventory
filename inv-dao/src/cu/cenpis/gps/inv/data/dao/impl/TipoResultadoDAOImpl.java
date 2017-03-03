package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.TipoResultadoDAO;
import cu.cenpis.gps.inv.data.entity.TipoResultado;
import org.springframework.stereotype.Repository;

@Repository
public class TipoResultadoDAOImpl extends AbstractHibernateDAO<TipoResultado, java.lang.Integer>
		implements TipoResultadoDAO {

	public TipoResultadoDAOImpl() {
		super(TipoResultado.class);
		System.out.println("TipoResultadoDAOImpl");
	}

}

