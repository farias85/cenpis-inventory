/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.util;

import java.util.ResourceBundle;

/**
 *
 * @author farias-i5
 */
public class Bundle {

    private static final String bundlePath = "/Bundle";

    public static String getString(String message) {
        return ResourceBundle.getBundle(bundlePath).getString(message);
    }
}
