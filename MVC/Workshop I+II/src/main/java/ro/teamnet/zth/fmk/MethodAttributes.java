package ro.teamnet.zth.fmk;

import java.util.ArrayList;
import java.util.List;

/**
 * MethodAttributes.java
 */
public class MethodAttributes {

    String controllerClass;
    String methodName;
    String methodType;
    ArrayList<String> methodParams;
    ArrayList<Class> methodTypes;


    public ArrayList<String> getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(ArrayList<String> methodParams) {
        this.methodParams = methodParams;
    }

    public ArrayList<Class> getMethodTypes() {
        return methodTypes;
    }

    public void setMethodTypes(ArrayList<Class> methodTypes) {
        this.methodTypes = methodTypes;
    }

    public String getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(String controllerClass) {
        this.controllerClass = controllerClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        MethodAttributes that = (MethodAttributes) o;

        if(controllerClass != null ? !controllerClass.equals(that.controllerClass) : that.controllerClass != null) {
            return false;
        }
        if(methodType != null ? !methodName.equals(that.methodName) : that.methodName != null) {
            return false;
        }
        if(methodType != null ? !methodType.equals(that.methodType) : that.methodType != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = controllerClass != null ? controllerClass.hashCode() : 0;
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        result = 31 * result + (methodType != null ? methodType.hashCode() : 0);
        return result;
    }
}
