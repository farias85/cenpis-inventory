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
package cu.cenpis.gps.inv.data.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Interfaz genérica que describe el comportamiento general de todos las clase
 * de acceso a datos.
 *
 * @param <T> Parametro generico que indica la clase entidad que va a ser
 * persistida
 * @param <I> Parametro generico que indica de que tipo es el ID de la clase
 * entidad que va a ser persistida
 */
public abstract interface AbstractDAO<T, I> {

    public abstract Serializable create(T object);

    public abstract T edit(T object);

    public abstract void remove(T object);

    public abstract void removeById(I id);

    public abstract boolean exist(I id);

    public abstract List<T> findAll();

    public abstract T find(I id);

    public abstract List<T> findNamedQuery(String namedQuery);

    public abstract List<T> findNamedQuery(String namedQuery, String paramName, Object value);
    
    public abstract List<T> findNamedQuery(String namedQuery, HashMap<String, Object> params);

    public abstract List<T> findByExample(final T exampleEntity);
}
