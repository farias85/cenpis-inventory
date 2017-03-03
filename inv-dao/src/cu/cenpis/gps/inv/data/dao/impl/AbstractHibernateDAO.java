/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author farias-i5
 *
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
