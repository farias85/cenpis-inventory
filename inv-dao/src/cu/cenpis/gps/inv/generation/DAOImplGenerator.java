/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.generation;

import static cu.cenpis.gps.inv.generation.ClassGenerator.path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/**
 *
 * @author farias-i5
 */
public class DAOImplGenerator extends ClassGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("DAOImpl GENERATOR RUNNING...");
        File f = new File(path + entityDir);

        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
                generateDAOImpls(classSingleName);
            }
        }
    }

    private static void generateDAOImpls(String className) {
        Field id = getIdField(className);
        PrintWriter pw;
        try {
            pw = new PrintWriter(path + daoImplDir + className + "DAOImpl.java");
            pw.println("package " + dot(daoImplDir).substring(0, daoImplDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(daoDir) + className + "DAO;");
            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import org.springframework.stereotype.Repository;");

            pw.println();

            pw.println("@Repository");
            pw.println("public class " + className
                    + "DAOImpl extends AbstractHibernateDAO<" + className + ", "
                    + transform(id.getType().getName()) + ">");
            pw.println("		implements " + className + "DAO {");
            pw.println();

            pw.println("	public " + className + "DAOImpl() {");
            pw.println("		super(" + className + ".class);");
            pw.println("		System.out.println(\"" + className + "DAOImpl\");");
            pw.println("	}");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
