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
 * Created by Vladimir Ortiz Grave de Peralta <vortiz@uo.edu.cu> on 28/02/2017.
 */
package cu.cenpis.gps.inv.read;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {

    private final ApplicationContext context;

    private Context() {
        context = new ClassPathXmlApplicationContext("cu/cenpis/gps/inv/config/mvc-dispatcher-servlet.xml");
    }

    private static Context mContext;

    public static Object getBean(String bean) {
        if (mContext == null) {
            mContext = new Context();
        }
        return mContext.context.getBean(bean);
    }

//    public static void main(String[] args) {
//        UsuarioService usuarioService = (UsuarioService) Context.getBean("usuarioServiceImpl");
//    }
}
