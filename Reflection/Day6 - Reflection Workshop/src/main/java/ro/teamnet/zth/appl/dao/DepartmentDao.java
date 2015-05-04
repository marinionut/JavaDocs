package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Ionutz on 04.05.2015.
 */
public class DepartmentDao {
    EntityManager entityManager = new EntityManagerImpl();


    public Department insertDepartment(Department department) {
        return (Department) entityManager.insert(department);
    }


    public Department updateDepartment(Department department) {
        return entityManager.update(department);
    }


    public void deleteDepartment(Department department) {
        entityManager.delete(department);
    }


    public List<Department> getAllDepartments() {
        return entityManager.findAll(Department.class);
    }


    public Department getDepartmentById(Object id) {
        return entityManager.findById(Department.class, id);
    }
}
