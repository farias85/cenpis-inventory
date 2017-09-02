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
package cu.cenpis.gps.inv.controller.converter;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @param <T>
 * @param <I>
 * @param <Controller>
 */
public class BaseConverter<T extends Object, I extends Serializable, Controller extends BaseConverterDelegate<T, I>>
        implements Converter {

    private Class<T> entityClass;
    private Class<I> idClass;

    public BaseConverter(Class<T> entityClass, Class<I> idClass) {
        this.entityClass = entityClass;
        this.idClass = idClass;
    }

    public BaseConverter() {
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        String className = entityClass.getSimpleName();
        String paramName = Character.toLowerCase(className.charAt(0)) + className.substring(1);

        Controller controller = (Controller) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, paramName + "Controller");
        
        return controller.findItem(getKey(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        try {
            T my = (T) object;
            Method idm = getIdMethod();
            I value = (I) (idm.invoke(my));
            return getStringKey(value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}",
                    new Object[]{object, object.getClass().getName(), entityClass.getName()});
            return null;
        }
    }

    private Method getIdMethod() {

        Method mid = null;
        Method[] mms = entityClass.getMethods();
        for (Method m : mms) {
            if (m.getName().startsWith("getId") && (m.getName().equals("getId")
                    || Character.isUpperCase(m.getName().charAt(5)))) {
                mid = m;
                break;
            }
        }
        return mid;
    }

    private I getKey(String value) {

        Class[] argsClass = new Class[]{String.class};
        Constructor constructor = null;

        try {
            constructor = idClass.getConstructor(argsClass);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(BaseConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        I key = (I) createObject(constructor, new Object[]{value});

        return key;
    }

    private String getStringKey(I value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    private Object createObject(Constructor constructor, Object[] arguments) {

        Object object = null;
        try {
            object = constructor.newInstance(arguments);
            return object;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }
}
