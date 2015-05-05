package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.EmployeesDao;
import ro.teamnet.zth.appl.domain.Employees;
import ro.teamnet.zth.appl.domain.Jobs;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by Ionutz on 05.05.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeesDaoTest {
    static Employees employee = new Employees();
    EmployeesDao employeeDao = new EmployeesDao();

    @Test
    public void aTestInsertEmployee() {
        employee.setId(229);
        employee.setFirstName("Test");
        employee.setLastName("Test");
        employee.setEmail("test@mail.com");
        employee.setPhoneNumber("07600000");
        employee.setHireDate(new Date(System.currentTimeMillis()));
        employee.setJobId("HR - test2");
        employee.setSalary(new BigDecimal(3000));
        employee.setCommissionPct(new BigDecimal(0));
        employee = employeeDao.insertEmployee(employee);
        System.out.println(employee);
       // System.out.println(employeeDao.getEmployeeById(employee.getId()));
        Assert.assertEquals(employeeDao.getEmployeeById(employee.getId()), employee);
    }

    @Test
    public void bTestUpdateEmployee() {
        EntityManager entityManager = new EntityManagerImpl();
        employee = entityManager.findById(Employees.class, 120);
        employee.setFirstName("Test");
        employee = employeeDao.updateEmployee(employee);
        Assert.assertEquals(employeeDao.getEmployeeById(employee.getId()), employee);
    }

    @Test
    public void cTestDeleteEmployee() {
        employeeDao.deleteEmployee(employee);
        Employees empById = employeeDao.getEmployeeById(employee.getId());

        Assert.assertNull(empById);
    }

    @Test
    public void dGetAllEmployees() {
        List<Employees> oldLoc = employeeDao.getAllEmployees();
        //add new employee
        employee.setId(237);
        employee.setFirstName("Test2");
        employee.setLastName("Test2");
        employee.setEmail("test2@mail.com");
        employee.setPhoneNumber("07600002");
        employee.setHireDate(new Date(System.currentTimeMillis()));
        employee.setJobId("HR - test");
        employee.setSalary(new BigDecimal(3000));
        employee.setCommissionPct(new BigDecimal(0));
        employee = employeeDao.insertEmployee(employee);
        List<Employees> newLoc = employeeDao.getAllEmployees();

        Assert.assertEquals(oldLoc.size(), newLoc.size() - 1);
    }
}
