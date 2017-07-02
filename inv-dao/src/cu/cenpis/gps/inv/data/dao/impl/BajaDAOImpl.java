package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.BajaDAO;
import cu.cenpis.gps.inv.data.entity.Baja;
import org.springframework.stereotype.Repository;

@Repository
public class BajaDAOImpl extends AbstractHibernateDAO<Baja, java.lang.Integer>
        implements BajaDAO {

    public BajaDAOImpl() {
        super(Baja.class);
        System.out.println("BajaDAOImpl");
    }
}
