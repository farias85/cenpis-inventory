/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.service.impl;

import cu.cenpis.gps.inv.data.dao.AbstractDAO;
import cu.cenpis.gps.inv.data.service.BaseService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author farias-i5
 * @param <T>
 * @param <I>
 * @param <TDAO>
 */
@Service
@Transactional
public class BaseServiceImpl<T extends Object, I extends Serializable, TDAO extends AbstractDAO<T, I>>
        implements BaseService<T, I>, Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    protected TDAO dao;

    @Override
    public Serializable create(T object) {
        return dao.create(object);
    }

    @Override
    public T edit(T object) {
        return dao.edit(object);
    }

    @Override
    public void remove(T object) {
        dao.remove(object);
    }

    @Override
    public void removeById(I id) {
        dao.removeById(id);
    }

    @Override
    public boolean exist(I id) {
        return dao.exist(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public T find(I id) {
        return dao.find(id);
    }

    @Override
    public List<T> findNamedQuery(String namedQuery) {
        return dao.findNamedQuery(namedQuery);
    }

    @Override
    public List<T> findNamedQuery(String namedQuery, String paramName, Object value) {
        return dao.findNamedQuery(namedQuery, paramName, value);
    }
    
    @Override
    public List<T> findNamedQuery(String namedQuery, HashMap<String, Object> params) {
        return dao.findNamedQuery(namedQuery, params);
    }

    @Override
    public List<T> findByExample(T exampleEntity) {
        return dao.findByExample(exampleEntity);
    }

}
