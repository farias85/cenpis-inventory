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

import static cu.cenpis.gps.inv.generation.ClassGenerator.dot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

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
