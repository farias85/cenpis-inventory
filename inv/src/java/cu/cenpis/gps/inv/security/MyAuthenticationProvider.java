package cu.cenpis.gps.inv.security;

import cu.cenpis.gps.inv.data.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

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

//        try {
//            String email = authentication.getPrincipal().toString();
//            String password = authentication.getCredentials().toString();
//            
//            Usuario usuario = new Usuario();
//            usuario.setEmail(email);
//            usuario = usuarioService.userAuthentication(usuario);
//
//            if (usuario != null && SecuredPassword.validatePassword(password, usuario.getContrasenna())) {
//                result = createSuccessAuthentication(authentication, usuario);
//                UsuarioController uc = JsfUtil.getController(UsuarioController.class);
//                uc.setActiveUser(usuario);
//            }
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
//            Logger.getLogger(MyAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return result;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    protected Authentication createSuccessAuthentication(final Authentication authentication/*, final Usuario usuario*/) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details

        List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
//        List<Rol> rl = usuarioService.getRolList(usuario);
//        rl.stream().forEach((var) -> {
//            AUTHORITIES.add(new SimpleGrantedAuthority(var.getNombre()));
//        });

        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), AUTHORITIES);

        result.setDetails(authentication.getDetails());

        return result;
    }
}
