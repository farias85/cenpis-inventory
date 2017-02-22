package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.data.dao.UsuarioDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, java.lang.Long, UsuarioDAO>
        implements UsuarioService {

    public UsuarioServiceImpl() {
        System.out.println("UsuarioServiceImpl()");
    }
}

