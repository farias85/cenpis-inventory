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

public class ServiceGenerator extends ClassGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Service GENERATOR RUNNING...");
        File f = new File(path + entityDir);

        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
                generateServices2(classSingleName);
            }
        }
    }
    
    private static void generateServices2(String className) {
        
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + serviceDir + className + "Service.java");
            pw.println("package " + dot(serviceDir).substring(0, serviceDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(entityDir) + className + ";");            
            pw.println();

            pw.println("public interface " + className + "Service extends BaseService<" + className + ", " + id.getType().getName() + "> {");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
        }
    }

    private static void generateServices(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + serviceDir + className + "Service.java");
            pw.println("package " + dot(serviceDir).substring(0, serviceDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import java.util.List;");
            pw.println();

            pw.println("public interface " + className + "Service {");
            pw.println();

            pw.println("public Long create(" + className + " " + paramName + ");");
            pw.println();

            pw.println("public " + className + " edit(" + className + " " + paramName + ");");
            pw.println();

            pw.println("public void remove(" + className + " " + paramName + ");");
            pw.println();

            pw.println("public void removeById(" + id.getType().getName() + " id);");
            pw.println();

            pw.println("public boolean exist(" + id.getType().getName() + " id);");
            pw.println();

            pw.println("public List<" + className + "> findAll();");
            pw.println();

            pw.println("public " + className + " find(" + id.getType().getName() + " id);");
            pw.println();

            pw.println("public List<" + className + "> findNamedQuery(String namedQuery);");
            pw.println();

            pw.println("public List<" + className + "> findNamedQuery(String namedQuery, String paramName, Object value);");
            pw.println();

            pw.println("public List<" + className + "> findByExample(final " + className + " exampleEntity);");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
        }
    }

}
