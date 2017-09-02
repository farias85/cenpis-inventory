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

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * This class provides a custom AuthenticationDetail which stores the connection
 * type method (Oracle/Ldap)
 *
 */
public class MyAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    /**
     * returns the {@link WebAuthenticationDetails} according to LuxFact rule
     * 
     * @param context
     * @return 
     */
    @Override
    public WebAuthenticationDetails buildDetails(final HttpServletRequest context) {    	
        return new MyAuthenticationDetails(context);
    }

}
