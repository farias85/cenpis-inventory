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

public class ControllerGenerator extends ClassGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("ControllerGenerator GENERATOR RUNNING...");
        File f = new File(path + entityDir);

        if (f.isDirectory()) {
            String[] ll = f.list(new JavaExtentionFilter());
            for (String s : ll) {
                String classSingleName = s.substring(0, s.length() - 5);
                generateControllers(classSingleName);
                generateConverters2(classSingleName);
            }
        }
    }

    private static void generateControllers(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + controllerDir + className + "Controller.java");
            pw.println("package " + dot(controllerDir).substring(0, controllerDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(serviceDir) + className + "Service;");
            pw.println("import " + dot(entityDir) + className + ";");

            pw.println("import cu.cenpis.gps.inv.util.Bundle;");
            pw.println("import javax.annotation.PostConstruct;");
            pw.println();

            pw.println("public class " + className + "Controller extends BaseController<" + className + ", " + id.getType().getName() + "> {");
            pw.println();

            pw.println("    private " + className + "Service " + paramName + "Service;");
            pw.println();

            pw.println("    public " + className + "Service get" + className + "Service() {");
            pw.println("        return " + paramName + "Service;");
            pw.println("    }");
            pw.println();

            pw.println("    public void set" + className + "Service(" + className + "Service " + paramName + "Service) {");
            pw.println("        this." + paramName + "Service = " + paramName + "Service;");
            pw.println("    }");
            pw.println();

            pw.println("    public " + className + "Controller() {");
            pw.println("        super(" + className + ".class);");
            pw.println("    }");
            pw.println();

            pw.println("    @PostConstruct");
            pw.println("    @Override");
            pw.println("    public void init() {");
            pw.println("        super.setFacade(" + paramName + "Service);");
            pw.println("    }");
            pw.println();

            pw.println("    @Override");
            pw.println("    public void create() {");
            pw.println("        super.create(Bundle.getString(\"" + className + "Created\"));");
            pw.println("    }");
            pw.println();

            pw.println("    @Override");
            pw.println("    public void update() {");
            pw.println("        super.update(Bundle.getString(\"" + className + "Updated\"));");
            pw.println("    }");
            pw.println();

            pw.println("    @Override");
            pw.println("    public void destroy() {");
            pw.println("        super.destroy(Bundle.getString(\"" + className + "Deleted\"));");
            pw.println("    }");

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateConverters2(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + controllerDir + "converter/" + className + "Converter.java");
            pw.println("package " + dot(controllerDir) + "converter;");
            pw.println();

            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import " + dot(controllerDir) + className + "Controller;");
            pw.println("import javax.faces.convert.FacesConverter;");
            pw.println();

            pw.println("@FacesConverter(forClass = " + className + ".class)");
            pw.println("public class " + className + "Converter extends BaseConverter<" + className + ", " + id.getType().getName() + ", " + className + "Controller> {");
            pw.println();

            pw.println("    public " + className + "Converter() {");
            pw.println("        super(" + className + ".class, " + id.getType().getName() + ".class);");
            pw.println("    }");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateConverters(String className) {

        String paramName = Character.toLowerCase(className.charAt(0)) + "" + className.substring(1);
        Field id = getIdField(className);

        PrintWriter pw;
        try {
            pw = new PrintWriter(path + controllerDir + className + "Converter.java");
            pw.println("package " + dot(controllerDir).substring(0, controllerDir.length() - 1) + ";");
            pw.println();

            pw.println("import " + dot(entityDir) + className + ";");
            pw.println("import javax.faces.component.UIComponent;");
            pw.println("import javax.faces.context.FacesContext;");
            pw.println("import javax.faces.convert.Converter;");
            pw.println("import javax.faces.convert.FacesConverter;");
            pw.println();

            pw.println("@FacesConverter(forClass = " + className + ".class, value = \"" + paramName + "Converter\")");
            pw.println("public class " + className + "Converter implements Converter {");
            pw.println();

            pw.println("    @Override");
            pw.println("    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {");
            pw.println("        if (value == null || value.length() == 0) {");
            pw.println("            return null;");
            pw.println("        }");
            pw.println("        " + className + "Controller controller = (" + className + "Controller) facesContext.getApplication().getELResolver().");
            pw.println("                getValue(facesContext.getELContext(), null, \"" + paramName + "Controller\");");
            pw.println("        return controller.getFacade().find(getKey(value));");
            pw.println("    }");
            pw.println();

            pw.println("    " + id.getType().getName() + " getKey(String value) {");
            pw.println("        " + id.getType().getName() + " key;");
            pw.println("        key = " + id.getType().getName() + ".valueOf(value);");
            pw.println("        return key;");
            pw.println("    }");
            pw.println();

            pw.println("    @Override");
            pw.println("    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {");
            pw.println("        if (object == null) {");
            pw.println("            return null;");
            pw.println("        }");
            pw.println("        return object.toString();");
            pw.println("    }");
            pw.println();

            pw.println("}");
            pw.println();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
