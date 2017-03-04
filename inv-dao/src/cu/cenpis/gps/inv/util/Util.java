/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 * @author farias-i5
 */
public class Util {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    public static String toSlug(String text) {
        String nowhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Devuelve los elementos contenidos en la lista second y q no están en la
     * lista first
     *
     * @param first
     * @param second
     */
    public static List<String> compare(List<String> first, List<String> second) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < second.size(); i++) {
            if (!existe(first, second.get(i))) {
                result.add(second.get(i));
            }
        }
        return result;
    }

    private static boolean existe(List<String> list, String mString) {
        for (String value : list) {
            if (value.equals(mString)) {
                return true;
            }
        }
        return false;
    }
}
