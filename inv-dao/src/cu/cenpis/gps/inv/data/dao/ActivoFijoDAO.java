package cu.cenpis.gps.inv.data.dao;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import java.util.List;

public interface ActivoFijoDAO extends AbstractDAO<ActivoFijo, java.lang.Long> {

    public List<ActivoFijo> findByMultipleRotulos(List<String> rList);
    public List<ActivoFijo> findNuevos();
    public List<ActivoFijo> findYaNoEstan();
}

