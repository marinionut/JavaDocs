package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParameters;
import ro.teamnet.zth.app.domain.Employee;
import ro.teamnet.zth.app.service.EmployeeService;
import ro.teamnet.zth.app.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionutz on 06.05.2015.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        /*
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setId(10);
        e1.setFirstName("Test1");
        e1.setLastName("Random1");
        employees.add(e1);
        Employee e2 = new Employee();
        e2.setId(11);
        e2.setFirstName("Test2");
        e2.setLastName("Random2");
        employees.add(e2);

        return employees;
        */

        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.findAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParameters(paramName = "idEmployee", paramType = String.class)String idEmployee) {

        /*Employee e = new Employee();
        e.setId(10);
        e.setFirstName("Test");
        e.setLastName("Random");
        return e;*/

        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.findOneEmployee(Integer.parseInt(idEmployee));
    }
}
