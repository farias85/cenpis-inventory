package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.SobranteDAO;
import cu.cenpis.gps.inv.data.entity.Sobrante;
import org.springframework.stereotype.Repository;

@Repository
public class SobranteDAOImpl extends AbstractHibernateDAO<Sobrante, java.lang.Long>
        implements SobranteDAO {

    public SobranteDAOImpl() {
        super(Sobrante.class);
        System.out.println("SobranteDAOImpl");
    }
}
