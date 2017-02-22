/**
 *
 */
package cu.cenpis.gps.inv.data.dao;

import java.util.List;

/**
 * Interfaz genérica que describe el comportamiento general de todos las clase
 * de acceso a datos.
 *
 * @author farias-i5
 *
 * @param <T> Parametro generico que indica la clase entidad que va a ser
 * persistida
 * @param <I> Parametro generico que indica de que tipo es el ID de la clase
 * entidad que va a ser persistida
 */
public abstract interface AbstractDAO<T, I> {

    public abstract Long create(T object);

    public abstract T edit(T object);

    public abstract void remove(T object);

    public abstract void removeById(I id);

    public abstract boolean exist(I id);

    public abstract List<T> findAll();

    public abstract T find(I id);

    public abstract List<T> findNamedQuery(String namedQuery);

    public abstract List<T> findNamedQuery(String namedQuery, String paramName, Object value);

    public abstract List<T> findByExample(final T exampleEntity);
}
