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

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Field;

/**
 * Genera las clases de la capa de acceso a datos Spring 4 - Hibernate 4
 */
public class ClassGenerator {

    protected static final String path = "src/";
    protected static final String purl = "cu/cenpis/gps/inv/";

    protected static final String entityDir = purl + "data/entity/";
    protected static final String daoDir = purl + "data/dao/";
    protected static final String daoImplDir = purl + "data/dao/impl/";

    protected static final String serviceDir = purl + "data/service/";
    protected static final String serviceImplDir = purl + "data/service/impl/";

    protected static final String controllerDir = purl + "controller/";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("DAO GENERATOR RUNNING...");
        File f = new File(path + entityDir);

        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
//                generateDAOMappings(classSingleName);
//                generateServiceGetBean(classSingleName);
                generateJSFManagedBeansXML(classSingleName);
            }
        }
    }

    protected static Field getIdField(String className) {

        Field id = null;
        try {
            Class cc = Class.forName(dot(entityDir) + className);
            Field[] ff = cc.getDeclaredFields();
            

            for (Field f : ff) {
                if (f.getName().startsWith("id") && (f.getName().equals("id")
                        || Character.isUpperCase(f.getName().charAt(2)))) {
                    id = f;
                    break;
                }
            }
            System.out.println(id.getName() + " " + id.getType().getName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        return id;
    }

    protected static String dot(String purl) {
        return purl.replace("/", ".");
    }

    protected static class JavaExtentionFilter implements FilenameFilter {

        public boolean accept(File dir, String name) {
            return name.endsWith(".java") && !name.endsWith("Id.java");
        }
    }

    public static void generateServiceGetBean(String className) {
        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        System.out.println(className + "Service " + paramName + "Service = (" + className + "Service) context.getBean(\"" + paramName + "ServiceImpl\");");
    }

    public static void generateDAOMappings(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);

        System.out.println("	<bean id=\"" + paramName + "DAO\"");
        System.out.println("		class=\"" + dot(daoImplDir) + className + "DAOImpl\">");
        System.out.println("		<property name=\"sessionFactory\">");
        System.out.println("			<ref bean=\"sessionFactory\" />");
        System.out.println("		</property>");
        System.out.println("	</bean>");
        System.out.println();
    }

    public static void generateJSFManagedBeansXML(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);

        System.out.println();
        System.out.println("<bean id=\"" + paramName + "Service\" class=\"" + dot(serviceImplDir) + className + "ServiceImpl\" />");
        System.out.println();
        System.out.println("<bean id=\"" + paramName + "Controller\" class=\"" + dot(controllerDir) + className + "Controller\" scope=\"session\">");
        System.out.println("    <property name=\"" + paramName + "Service\">");
        System.out.println("        <ref bean=\"" + paramName + "Service\" />");
        System.out.println("    </property>");
        System.out.println("</bean>");
        System.out.println();

    }

    protected static String transform(String name) {
        if (name.equals("int")) {
            return "Integer";
        }
        return name;
    }
}
