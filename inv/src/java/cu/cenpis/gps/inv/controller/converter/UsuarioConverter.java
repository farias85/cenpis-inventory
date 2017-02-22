package cu.cenpis.gps.inv.controller.converter;

import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.controller.UsuarioController;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter extends BaseConverter<Usuario, java.lang.Long, UsuarioController> {

    public UsuarioConverter() {
        super(Usuario.class, java.lang.Long.class);
    }

}

