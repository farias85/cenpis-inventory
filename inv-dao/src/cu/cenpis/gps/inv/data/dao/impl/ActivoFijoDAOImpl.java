package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.ActivoFijoDAO;
import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

@Repository
public class ActivoFijoDAOImpl extends AbstractHibernateDAO<ActivoFijo, java.lang.Long>
        implements ActivoFijoDAO {

    public ActivoFijoDAOImpl() {
        super(ActivoFijo.class);
        System.out.println("ActivoFijoDAOImpl");
    }

    private List<String> findRotuloByRevision(boolean activo) {
        int mActivo = activo ? 1 : 0;
        Session session = hibernateUtil.getSession();

        String queryString = "SELECT"
                + "  activo_fijo.rotulo"
                + "  FROM"
                + "  activo_fijo"
                + "  INNER JOIN revision ON (revision.id_revision = activo_fijo.revision)"
                + "  WHERE"
                + "  revision.activo = :activo"
                + "  ORDER BY"
                + "  activo_fijo.rotulo";

        SQLQuery query = session.createSQLQuery(queryString).
                addScalar("activo_fijo.rotulo", new StringType());

        query.setInteger("activo", mActivo);
        List<Object[]> rows = query.list();

        List<String> result = new ArrayList<>();
        for (Object row : rows) {
            result.add(row.toString());
        }

        return result;
    }

    @Override
    public List<ActivoFijo> findNuevos() {
        List<String> activos = findRotuloByRevision(true);
        List<String> desactivados = findRotuloByRevision(false);
        List<String> rotulosDiferencia = Util.compare(desactivados, activos);
        return findByMultipleRotulos(rotulosDiferencia);
    }

    @Override
    public List<ActivoFijo> findYaNoEstan() {
        List<String> activos = findRotuloByRevision(true);
        List<String> desactivados = findRotuloByRevision(false);
        List<String> rotulosDiferencia = Util.compare(activos, desactivados);
        return findByMultipleRotulos(rotulosDiferencia);
    }

    @Override
    public List<ActivoFijo> findByMultipleRotulos(List<String> rList) {
        Session session = hibernateUtil.getSession();
        String queryString = "SELECT a FROM ActivoFijo a WHERE 1 > 2";
        for (int i = 0; i < rList.size(); i++) {
            queryString += " OR a.rotulo = :r" + i;
        }
        queryString += " ORDER BY a.rotulo";
        Query query = session.createQuery(queryString);
        for (int i = 0; i < rList.size(); i++) {
            query.setParameter("r" + i, rList.get(i));
        }
        return query.list();
    }
}
