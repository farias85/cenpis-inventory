package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.RolDAO;
import cu.cenpis.gps.inv.data.entity.Rol;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImpl extends AbstractHibernateDAO<Rol, java.lang.Long>
		implements RolDAO {

	public RolDAOImpl() {
		super(Rol.class);
		System.out.println("RolDAOImpl");
	}

}

