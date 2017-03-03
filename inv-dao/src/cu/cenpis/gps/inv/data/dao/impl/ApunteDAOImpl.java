package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.ApunteDAO;
import cu.cenpis.gps.inv.data.entity.Apunte;
import org.springframework.stereotype.Repository;

@Repository
public class ApunteDAOImpl extends AbstractHibernateDAO<Apunte, java.lang.Integer>
		implements ApunteDAO {

	public ApunteDAOImpl() {
		super(Apunte.class);
		System.out.println("ApunteDAOImpl");
	}

}

