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
package cu.cenpis.gps.inv.data.dao.impl;

import cu.cenpis.gps.inv.data.dao.AbstractDAO;
import cu.cenpis.gps.inv.data.util.HibernateUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @param <T> Parametro generico que indica la clase entidad que va a ser
 * persistida
 * @param <I> Parametro generico que indica de que tipo es el ID de la clase
 * entidad que va a ser persistida
 */
@Repository
public class AbstractHibernateDAO<T extends Object, I extends Serializable> implements AbstractDAO<T, I>, Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    protected HibernateUtil hibernateUtil;

    private Class<T> entityClass;

    protected AbstractHibernateDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractHibernateDAO() {
    }

    @Override
    public Serializable create(T object) {
        return hibernateUtil.create(object);
    }

    @Override
    public T edit(T object) {
        return hibernateUtil.update(object);
    }

    @Override
    public void remove(T object) {
        hibernateUtil.delete(object);
    }

    @Override
    public void removeById(I id) {
        T obj = find(id);
        if (obj != null) {
            remove(obj);
        }
    }

    @Override
    public boolean exist(I id) {
        return find(id) != null;
    }

    @Override
    public List<T> findAll() {
        return hibernateUtil.fetchAll(entityClass);
    }

    @Override
    public T find(I id) {
        return (T) hibernateUtil.fetchById(id, entityClass);
    }

    @Override
    public List<T> findNamedQuery(String namedQuery) {
        return hibernateUtil.getSession().getNamedQuery(namedQuery).list();
    }

    @Override
    public List<T> findNamedQuery(String namedQuery, String paramName, Object value) {
        return hibernateUtil.getSession().getNamedQuery(namedQuery).setParameter(paramName, value).list();
    }
       
    @Override
    public List<T> findNamedQuery(String namedQuery, HashMap<String, Object> params) {
        
        Query query = hibernateUtil.getSession().getNamedQuery(namedQuery);
        Object[] keys = params.keySet().toArray();
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(keys[i].toString(), params.get(keys[i].toString()));
        }        
        return query.list();
    }
    
    @Override
    public List<T> findByExample(T exampleEntity) {
        final Example example = Example.create(exampleEntity).
                excludeZeroes().
                enableLike(MatchMode.ANYWHERE).
                ignoreCase();
        return hibernateUtil.getSession().createCriteria(entityClass).add(example).list();
    }
}
