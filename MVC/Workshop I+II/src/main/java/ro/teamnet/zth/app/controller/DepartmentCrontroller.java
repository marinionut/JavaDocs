package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.app.domain.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionutz on 06.05.2015.
 */
@MyController(urlPath = "/departments")
public class DepartmentCrontroller {

    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartment() {

        List<Department> departments = new ArrayList<>();
        Department d1 = new Department();
        d1.setId(13);
        d1.setDepartmentName("Test1");
        d1.setLocations(10001);
        departments.add(d1);

        Department d2 = new Department();
        d2.setId(14);
        d2.setDepartmentName("Test2");
        d2.setLocations(10000);
        departments.add(d2);

        return departments;

    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneDepartment(){
        return "oneRandomDepartment";
    }
}
