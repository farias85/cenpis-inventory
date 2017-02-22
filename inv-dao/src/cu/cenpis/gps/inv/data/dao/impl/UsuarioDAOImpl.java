package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.UsuarioDAO;
import cu.cenpis.gps.inv.data.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl extends AbstractHibernateDAO<Usuario, java.lang.Long>
		implements UsuarioDAO {

	public UsuarioDAOImpl() {
		super(Usuario.class);
		System.out.println("UsuarioDAOImpl");
	}

}

