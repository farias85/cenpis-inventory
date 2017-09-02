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
package cu.cenpis.gps.inv.controller;

import cu.cenpis.gps.inv.controller.converter.BaseConverterDelegate;
import cu.cenpis.gps.inv.data.service.BaseService;
import cu.cenpis.gps.inv.util.Bundle;
import cu.cenpis.gps.inv.util.JsfUtil;
import cu.cenpis.gps.inv.util.JsfUtil.PersistAction;
import cu.cenpis.gps.inv.util.Util;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;

/**
 *
 * @param <T> Clase entidad que gestionara el controlador
 * @param <I> Tipo de dato del identificador de la clase
 */
public abstract class BaseController<T, I>
        implements Serializable, BaseConverterDelegate<T, I> {

    /**
     * *
     * Class de la clase entidad. Debe pasarse en el constructor por defecto
     */
    private Class<T> itemClass;

    /**
     * Servicio base. Luego se setea en las clases hijas mediante
     * PostConstructor la implementacion verdadera del servicio
     */
    protected BaseService<T, I> service;

    /**
     * Elemento seleccionado en el controlador desde la vista.
     */
    protected T selected;

    /**
     * Lista de todos los elementos con que funciona el controlador.
     */
    protected List<T> items = null;

    /**
     * Elementos de ayuda para facilitar el filtrado en cada uno de los
     * controladores
     */
    protected List<T> filtered = null;

    /**
     * BaseController Constructor
     *
     * @param itemClass Se llama dentro del constructor por defecto en las
     * clases hijas, para setear el tipo class de T
     */
    public BaseController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Empty constructor
     */
    public BaseController() {
    }

    /**
     * Initialize the concrete controller bean. Task is performed by the
     * concrete controller bean.
     * <p>
     * In addition, each controller for an entity that has Many-To-One
     * relationships, needs to establish references to those entities'
     * controllers in order to display their information from a context menu.
     */
    public abstract void init();

    public abstract void create();

    public abstract void update();

    public abstract void destroy();

    public String actionPrepareCreate() {
        this.prepareCreate();
        return "Create.jsf?faces-redirect=true";
    }

    public String actionCreate() {
        this.create();
        return actionCancel();
    }

    public String actionView() {
        return "View.jsf?faces-redirect=true";
    }

    public String actionPrepareEdit() {
        return "Edit.jsf?faces-redirect=trues";
    }

    public String actionEdit() {
        this.update();
        return actionCancel();
    }

    public String actionCancel() {
        selected = null;
        return "List.jsf?faces-redirect=true";
    }

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     *
     * @see prepareCreate()
     */
    protected void initializeEmbeddableKey() {
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
    }

    protected void create(String bundleMessage) {
        persist(PersistAction.CREATE, bundleMessage);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
//            getItems().add(selected);
        }
    }

    protected void update(String bundleMessage) {
        persist(PersistAction.UPDATE, bundleMessage);
//        if (!JsfUtil.isValidationFailed()) {
//            refreshItem(selected);
//        }
    }

    protected void destroy(String bundleMessage) {
        persist(PersistAction.DELETE, bundleMessage);
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.

            //filtered.remove(selected);
//            getItems().remove(selected);
//            selected = null;
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    protected void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    if (persistAction == PersistAction.DELETE) {
                        msg = "El elemento seleccionado no se puede eliminar.";
                    }
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, Bundle.getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                String msg = "";
                if (persistAction == PersistAction.DELETE) {
                    msg = "El elemento seleccionado no se puede eliminar.";
                    JsfUtil.addErrorMessage(msg);
                } else {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    JsfUtil.addErrorMessage(ex, Bundle.getString("PersistenceErrorOccured"));
                }
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @return a new, unmanaged Entity
     */
    public T prepareCreate() {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return this.selected;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean getRenderedCreate() {
        if (selected != null) {
            return true;
        }
        T obj = this.prepareCreate();
        return obj != null;
    }

    public BaseService<T, I> getService() {
        return service;
    }

    public void setService(BaseService<T, I> service) {
        this.service = service;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * Retrieve the current BaseService object so that other beans in this
     * package can perform additional data layer tasks (e.g. additional queries)
     *
     * @return the concrete BaseService associated with the concrete controller
     * bean.
     */
    public BaseService<T, I> getFacade() {
        return service;
    }

    public void setFacade(BaseService<T, I> facade) {
        this.service = facade;
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public List<T> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<T> filtered) {
        this.filtered = filtered;
    }

    public List<T> getItemsAvailableSelectMany() {
        //return getFacade().findAll();
        return getItems();
    }

    public List<T> getItemsAvailableSelectOne() {
        //return getFacade().findAll();
        return getItems();
    }

    /**
     * ***********************************************************************
     */
    /**
     * **********************Reflection Utils*********************************
     */
    /**
     * ***********************************************************************
     */
    private String queryMethod;

    /**
     * Especifica el nombre del método de T (preferiblemente un método get) que
     * es invocado para la ejecución de otros métodos, principalmente
     * autoCompleteQuery() y autoCompleteQueryItems()
     *
     * @return Cadena de caracteres del método
     * @see autoCompleteQuery
     * @see autoCompleteQueryItems
     */
    public String getQueryMethod() {
        return queryMethod;
    }

    public void setQueryMethod(String queryMethod) {
        this.queryMethod = queryMethod;
    }

    /**
     * Refresca en memoria el objeto enviado por parámetro en la lista de
     * elementos cargada por defecto.
     *
     * @param object El objeto
     */
    public void refreshItem(T object) {
        getItems().set(findItemPos(getIdValue(object)), object);
    }

    /**
     * Busca en los elementos items el objeto con el @param id
     *
     * @param id Identificador del objeto
     * @return El objeto en la lista items
     */
    @SuppressWarnings("null")
    @Override
    public T findItem(I id) {

        for (T item : getItems()) {
            I value = getIdValue(item);
            if (value.equals(id)) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, value + " equals to " + id);
                return item;
            }
        }
        return null;
    }

    /**
     * Devuelve el valor del identificador del objeto T
     *
     * @param item Objeto del cual se necesita saber el valor del id
     * @return Valor del identificador del objeto
     */
    public I getIdValue(T item) {
        Method idm = getIdMethod();
        I value = null;
        try {
            value = (I) (idm.invoke(item));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public Method getIdMethod() {

        Method mid = null;
        Method[] mms = itemClass.getMethods();
        for (Method m : mms) {
            if (m.getName().startsWith("getId") && (m.getName().equals("getId")
                    || Character.isUpperCase(m.getName().charAt(5)))) {
                mid = m;
                break;
            }
        }
        return mid;
    }

    public int findItemPos(I id) {

        int pos = 0;
        for (T item : getItems()) {
            I value = getIdValue(item);
            if (value.equals(id)) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, value + " equals to " + id);
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public List<String> autoCompleteQuery(String query) {

        List<String> result = new ArrayList<>();
        try {
            Method method = itemClass.getMethod(queryMethod);
            for (int i = 0; i < getItems().size(); i++) {
                T item = getItems().get(i);
                String returnValue = String.valueOf(method.invoke(item));
                if (Util.toSlug(returnValue).contains(Util.toSlug(query))) {
                    result.add(returnValue);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<T> autoCompleteQueryItems(String query) {

        List<T> result = new ArrayList<>();
        try {
            Method method = itemClass.getMethod(queryMethod);
            for (int i = 0; i < getItems().size(); i++) {
                T item = getItems().get(i);
                String returnValue = String.valueOf(method.invoke(item));
                if (Util.toSlug(returnValue).contains(Util.toSlug(query))) {
                    result.add(item);
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<String> getItemsAsStringList(String methodStr) {
        List<T> pitems = getItems();
        return getItemsAsStringList(pitems, methodStr);
    }

    public List<String> getItemsAsStringList(List<T> pitems) {
        return getItemsAsStringList(pitems, queryMethod);
    }

    public List<String> getItemsAsStringList() {
        List<T> pitems = getItems();
        return getItemsAsStringList(pitems);
    }

    public List<String> getItemsAsStringList(List<T> pitems, String methodStr) {
        List<String> result = new ArrayList<>();
        try {
            Method method = itemClass.getMethod(methodStr);
            for (int i = 0; i < pitems.size(); i++) {
                result.add(String.valueOf(method.invoke(pitems.get(i))));
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<String> getItemsAsStringListNotIn(List<T> pitems) {

        List<T> resultAux = new ArrayList<>(getItems());
        try {
            Method method = itemClass.getMethod(queryMethod);
            for (T itemI : pitems) {

                String valueI = String.valueOf(method.invoke(itemI));
                for (T itemJ : getItems()) {
                    String valueJ = String.valueOf(method.invoke(itemJ));
                    if (Util.toSlug(valueI).equalsIgnoreCase(Util.toSlug(valueJ))) {
                        resultAux.remove(itemJ);
                        break;
                    }
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getItemsAsStringList(resultAux, queryMethod);
    }

    public T findItemByStringMethod(String query, String methodStr) {

        try {
            Method method = itemClass.getMethod(methodStr);
            for (int i = 0; i < getItems().size(); i++) {
                T item = getItems().get(i);
                String returnValue = String.valueOf(method.invoke(item));
                if (Util.toSlug(returnValue).equals(Util.toSlug(query))) {
                    return item;
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public T findItemByStringMethod(String query) {
        return findItemByStringMethod(query, queryMethod);
    }
}
