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

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * this class stores data used for authentication (mainly the authentication
 * method)
 */
public class MyAuthenticationDetails extends WebAuthenticationDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String method;

    public MyAuthenticationDetails(final HttpServletRequest request) {
        super(request);
        method = request.getParameter("method");
    }

    public String getMethod() {
        return method;
    }

}
