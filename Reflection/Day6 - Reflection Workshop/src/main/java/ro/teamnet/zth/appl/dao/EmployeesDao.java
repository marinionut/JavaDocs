package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Employees;

import java.util.List;

/**
 * Created by Ionutz on 04.05.2015.
 */
public class EmployeesDao {
    EntityManager entityManager = new EntityManagerImpl();


    public Employees insertEmployee(Employees employee) {
        return (Employees) entityManager.insert(employee);
    }


    public Employees updateEmployee(Employees employee) {
        return entityManager.update(employee);
    }


    public void deleteEmployee(Employees employee) {
        entityManager.delete(employee);
    }


    public List<Employees> getAllEmployees() {
        return entityManager.findAll(Employees.class);
    }


    public Employees getEmployeeById(Object id) {
        return entityManager.findById(Employees.class, id);
    }
}
