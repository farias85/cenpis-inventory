/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.controller.converter;

/**
 *
 * @author farias-i5
 * @param <T>
 * @param <I>
 */
public interface BaseConverterDelegate<T, I> {
    
    public T findItem(I id);
}
