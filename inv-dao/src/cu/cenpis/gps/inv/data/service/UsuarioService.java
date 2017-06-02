package cu.cenpis.gps.inv.data.service;

import cu.cenpis.gps.inv.data.entity.Rol;
import cu.cenpis.gps.inv.data.entity.Usuario;
import java.util.List;

public interface UsuarioService extends BaseService<Usuario, java.lang.Long> {

    public Usuario userAuthentication(Usuario u);
    
    public List<Rol> getRolList(Usuario usuario);
    
    public boolean existe(Usuario usuario);
    
    public void refrescarSelected(Usuario usuario);
}

