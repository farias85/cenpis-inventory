package cu.cenpis.gps.inv.data.service;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import java.util.List;

public interface ActivoFijoService extends BaseService<ActivoFijo, java.lang.Long> {

    public List<ActivoFijo> findByMultipleRotulos(List<String> rList);
    public List<ActivoFijo> findNuevos();
    public List<ActivoFijo> findYaNoEstan();
}

