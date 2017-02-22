/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.generation;

import static cu.cenpis.gps.inv.generation.ClassGenerator.dot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/**
 *
 * @author farias-i5
 */
public class DAOGenerator extends ClassGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("DAO GENERATOR RUNNING...");
        File f = new File(path + entityDir);
        System.out.println(path + entityDir);
        
        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
                generateDAOs(classSingleName);
                System.out.println(s);
            }
        }
    }

    private static void generateDAOs(String className) {
        Field id = getIdField(className);
        PrintWriter pw;
        try {
            pw = new PrintWriter(path + daoDir + className + "DAO.java");
            pw.println("package " + dot(daoDir).substring(0, daoDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(entityDir) + className + ";");
            pw.println();

            pw.println("public interface " + className + "DAO extends AbstractDAO<" + className
                    + ", " + transform(id.getType().getName()) + "> {");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
        }
    }

}
