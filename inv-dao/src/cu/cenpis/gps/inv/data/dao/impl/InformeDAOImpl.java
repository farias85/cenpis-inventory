package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.InformeDAO;
import cu.cenpis.gps.inv.data.entity.Informe;
import org.springframework.stereotype.Repository;

@Repository
public class InformeDAOImpl extends AbstractHibernateDAO<Informe, java.lang.Integer>
		implements InformeDAO {

	public InformeDAOImpl() {
		super(Informe.class);
		System.out.println("InformeDAOImpl");
	}

}

