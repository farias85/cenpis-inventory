package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.AuditoriaDAO;
import cu.cenpis.gps.inv.data.entity.Auditoria;
import org.springframework.stereotype.Repository;

@Repository
public class AuditoriaDAOImpl extends AbstractHibernateDAO<Auditoria, java.lang.Long>
		implements AuditoriaDAO {

	public AuditoriaDAOImpl() {
		super(Auditoria.class);
		System.out.println("AuditoriaDAOImpl");
	}

}

