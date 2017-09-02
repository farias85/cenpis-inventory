/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 28/02/2017.
 */
package cu.cenpis.gps.inv.security;

import cu.cenpis.gps.inv.controller.UsuarioController;
import cu.cenpis.gps.inv.data.entity.Rol;
import cu.cenpis.gps.inv.data.entity.Usuario;
import cu.cenpis.gps.inv.data.service.UsuarioService;
import cu.cenpis.gps.inv.util.JsfUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyAuthenticationProvider implements AuthenticationProvider {

    private UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService UsuarioService) {
        this.usuarioService = UsuarioService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {

        Authentication result = isValid(authentication);
        if (!result.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        return result;
    }

    private Authentication isValid(final Authentication authentication) {

        Authentication result = authentication;
        System.out.println("Selected method: " + ((MyAuthenticationDetails) authentication.getDetails()).getMethod());

        try {
            String email = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario = usuarioService.userAuthentication(usuario);

            if (usuario != null && SecuredPassword.validatePassword(password, usuario.getContrasenna())) {
                result = createSuccessAuthentication(authentication, usuario);
                UsuarioController uc = JsfUtil.getController(UsuarioController.class);
                uc.setActiveUser(usuario);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(MyAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    protected Authentication createSuccessAuthentication(final Authentication authentication, final Usuario usuario) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details

        List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
        List<Rol> rl = usuarioService.getRolList(usuario);
        rl.stream().forEach((var) -> {
            AUTHORITIES.add(new SimpleGrantedAuthority(var.getNombre()));
        });

        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), AUTHORITIES);

        result.setDetails(authentication.getDetails());

        return result;
    }
}
