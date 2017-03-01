package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.dao.RolDAO;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.data.dao.UsuarioDAO;
import cu.cenpis.gps.inv.data.entity.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, java.lang.Long, UsuarioDAO>
        implements UsuarioService {

    @Autowired
    RolDAO rolDAO;

    public UsuarioServiceImpl() {
        System.out.println("UsuarioServiceImpl()");
    }

    @Override
    public Usuario userAuthentication(Usuario u) {
        return dao.userAuthentication(u);
    }

    @Override
    public List<Rol> getRolList(Usuario usuario) {
        return rolDAO.findNamedQuery("Rol.findByIdUsuario", "idUsuario", usuario.getIdUsuario());
    }
}
