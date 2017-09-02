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
package cu.cenpis.gps.inv.generation;

import static cu.cenpis.gps.inv.generation.ClassGenerator.path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

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
