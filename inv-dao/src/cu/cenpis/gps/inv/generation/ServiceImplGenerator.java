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

public class ServiceImplGenerator extends ClassGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("ServiceImplGenerator GENERATOR RUNNING...");
        File f = new File(path + entityDir);

        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
                generateServiceImpls2(classSingleName);
            }
        }
    }
    
    private static void generateServiceImpls2(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + serviceImplDir + className + "ServiceImpl.java");
            pw.println("package " + dot(serviceImplDir).substring(0, serviceImplDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(serviceDir) + className + "Service;");
            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import " + dot(daoDir) + className + "DAO;");
            pw.println();
            
            pw.println("import org.springframework.stereotype.Service;");
            pw.println("import org.springframework.transaction.annotation.Transactional;");
            pw.println();

            pw.println("@Service");
            pw.println("@Transactional");
            pw.println("public class " + className + "ServiceImpl extends BaseServiceImpl<" + className + ", " + id.getType().getName() + ", " + className + "DAO>");
            pw.println("        implements " + className + "Service {");
            pw.println();
            
            pw.println("    public " + className + "ServiceImpl() {");
            pw.println("        System.out.println(\"" + className + "ServiceImpl()\");");
            pw.println("    }");            

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void generateServiceImpls(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + serviceImplDir + className + "ServiceImpl.java");
            pw.println("package " + dot(serviceImplDir).substring(0, serviceImplDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(serviceDir) + className + "Service;");
            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import " + dot(daoDir) + className + "DAO;");
            pw.println();

            pw.println("import org.springframework.beans.factory.annotation.Autowired;");
            pw.println("import org.springframework.stereotype.Service;");
            pw.println("import org.springframework.transaction.annotation.Transactional;");
            pw.println();

            pw.println("import java.util.List;");
            pw.println();

            pw.println("@Service");
            pw.println("@Transactional");
            pw.println("public class " + className + "ServiceImpl implements " + className + "Service {");
            pw.println();

            pw.println("public " + className + "ServiceImpl() {");
            pw.println("    System.out.println(\"" + className + "ServiceImpl()\");");
            pw.println("}");
            pw.println();

            pw.println("@Autowired");
            pw.println("private " + className + "DAO " + paramName + "DAO;");
            pw.println();

            pw.println("@Override");
            pw.println("public Long create(" + className + " " + paramName + ") {");
            pw.println("    return " + paramName + "DAO.create(" + paramName + ");");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public " + className + " edit(" + className + " " + paramName + ") {");
            pw.println("    return " + paramName + "DAO.edit(" + paramName + ");");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public void remove(" + className + " " + paramName + ") {");
            pw.println("    " + paramName + "DAO.remove(" + paramName + ");");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public void removeById(" + id.getType().getName() + " id) {");
            pw.println("    " + paramName + "DAO.removeById(id);");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public boolean exist(" + id.getType().getName() + " id) {");
            pw.println("    return " + paramName + "DAO.exist(id);");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public List<" + className + "> findAll() {");
            pw.println("    return " + paramName + "DAO.findAll();");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public " + className + " find(" + id.getType().getName() + " id) {");
            pw.println("    return " + paramName + "DAO.find(id);");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public List<" + className + "> findNamedQuery(String namedQuery) {");
            pw.println("    return " + paramName + "DAO.findNamedQuery(namedQuery);");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public List<" + className + "> findNamedQuery(String namedQuery, String paramName, Object value) {");
            pw.println("    return " + paramName + "DAO.findNamedQuery(namedQuery, paramName, value);");
            pw.println("}");
            pw.println();

            pw.println("@Override");
            pw.println("public List<" + className + "> findByExample(final " + className + " exampleEntity) {");
            pw.println("    return " + paramName + "DAO.findByExample(exampleEntity);");
            pw.println("}");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
