/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farias-i5
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> first = new ArrayList<>();
        first.add(1);
        first.add(2);
        first.add(3);
        first.add(4);
        first.add(2);
        first.add(2);
        first.add(1);
        first.add(1);
        first.add(1);
        first.add(1);
        first.add(2);        

        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(2);
        second.add(5);
        second.add(4);
        second.add(4);
        second.add(4);
        second.add(4);
        for (int i = 0; i < 80000; i++) {
            second.add(2);
        }

//        List<Integer> result = compare(second, first);
        List<Integer> result = compare(first, second);
        for (Integer value : result) {
            System.out.println(value);
        }
    }

    /**
     * Devuelve los elementos contenidos en la lista second y q no están en la
     * lista first
     *
     * @param first
     * @param second
     */
    private static List<Integer> compare(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < second.size(); i++) {
            if (!existe(first, second.get(i))) {
                result.add(second.get(i));
            }
        }
        return result;
    }

    private static boolean existe(List<Integer> list, Integer mInt) {
        for (Integer value : list) {
            if (value.equals(mInt)) {
                return true;
            }
        }
        return false;
    }
}
