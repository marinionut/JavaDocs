package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.dao.DepartmentDao;
import ro.teamnet.zth.app.dao.EmployeeDao;
import ro.teamnet.zth.app.domain.Employee;

import java.util.List;

/**
 * Created by Ionutz on 07.05.2015.
 */

/**
 * 7.	 In pachetul ro.teamnet.zth.app.service creati clasa EmployeeServiceImpl.java,
 * care implementeaza interfata EmployeeService.java. Metoda findAllEmployees() va
 * returna toti utilizatorii din baza de date. Utilizati layaerul de DAO pentru a accesa baza de date.
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Integer id) {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getEmployeeById(id);
    }

}
