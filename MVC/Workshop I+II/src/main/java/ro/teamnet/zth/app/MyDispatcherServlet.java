package ro.teamnet.zth.app;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParameters;
import ro.teamnet.zth.app.controller.DepartmentCrontroller;
import ro.teamnet.zth.app.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ionutz on 06.05.2015.
 */
public class MyDispatcherServlet extends HttpServlet {
    HashMap<String, MethodAttributes> allowedMethods = new HashMap<>();
    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.app.controller");
            allowedMethods = getAllowedRequestMethods(classes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, MethodAttributes> getAllowedRequestMethods(Iterable<Class> classes) {
        for (Class controller : classes) {
            if(controller.isAnnotationPresent(MyController.class)) {
                MyController myControllerAnnotatiton = (MyController) controller.getAnnotation(MyController.class);
                String controllerUrlPath = myControllerAnnotatiton.urlPath();
                Method[] controllerMethods = controller.getMethods();
                for (Method controllerMethod : controllerMethods) {
                    if(controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                        MyRequestMethod methodAnnotation = (MyRequestMethod)controllerMethod.getAnnotation(MyRequestMethod.class);
                        String key = controllerUrlPath + methodAnnotation.urlPath();

                        MethodAttributes methodAttributes = new MethodAttributes();

                        methodAttributes.setControllerClass(controller.getName());
                        methodAttributes.setMethodName(controllerMethod.getName());
                        methodAttributes.setMethodType(methodAnnotation.methodType());
                        ArrayList<String> paramNames = new ArrayList<>();
                        ArrayList<Class> paramTypes = new ArrayList<>();
                        Annotation[][] parameterAnnotations = controllerMethod.getParameterAnnotations();
                        for (Annotation[] parameterAnnotation : parameterAnnotations) {
                            for (Annotation annotation : parameterAnnotation) {
                                MyRequestParameters myRequestParameters = (MyRequestParameters) annotation;

                                String paramName = myRequestParameters.paramName();
                                Class paramType = myRequestParameters.paramType();
                                paramNames.add(paramName);
                                paramTypes.add(paramType);

                            }

                        }
                        methodAttributes.setMethodParams(paramNames);
                        methodAttributes.setMethodTypes(paramTypes);
                        allowedMethods.put(key, methodAttributes);
                    }
                }
            }
        }

        return allowedMethods;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchreply(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void dispatchreply(HttpServletRequest req, HttpServletResponse resp) {

        Object r = null;
        try {
            r = dispatch(req, resp);
            reply(r, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyDispatchException e) {
            PrintWriter out = null;
            try {
                out = resp.getWriter();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            out.write("url-ul nu este mapat");
        }

    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) throws NoSuchMethodException {
        /*
        String pathInfo = req.getPathInfo();
        if(pathInfo.startsWith("/employees")) {
            EmployeeController ec = new EmployeeController();
            String allEmployees = ec.getAllEmployees();
            return allEmployees;
        } else if(pathInfo.startsWith("/departments")) {
            DepartmentCrontroller dc = new DepartmentCrontroller();
            String allDepartments = dc.getAllDepartment();
            return allDepartments;
        }
        return "Hello!";
        */

        String path = req.getPathInfo();

        MethodAttributes methodAttributes = allowedMethods.get(path);

        if(methodAttributes != null) {
            /*Apel Application Controller*/
            Class<?> controllerClass = null;
            try {
                controllerClass = Class.forName(methodAttributes.getControllerClass());
                Object instanceControllerClass = controllerClass.newInstance();
                int numberOfParams = methodAttributes.getMethodParams().size();
                Class[] paramTypes = new Class[numberOfParams];
                int i = 0;
                for (Class c : methodAttributes.getMethodTypes()) {
                        paramTypes[i++] = c;
                }
                Method m = instanceControllerClass.getClass().getDeclaredMethod(methodAttributes.getMethodName(), paramTypes);
                List<String> paramList = new ArrayList<>();
                for (String s : methodAttributes.getMethodParams()) {
                    paramList.add(req.getParameter(s));
                }
                Object r = m.invoke(instanceControllerClass, (String[]) paramList.toArray(new String[0]));
                return r;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        throw new MyDispatchException();
    }



    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, r);
    }
}
