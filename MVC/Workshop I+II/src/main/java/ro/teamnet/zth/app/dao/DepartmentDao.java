package ro.teamnet.zth.app.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.app.domain.Department;

import java.util.List;

/**
 * Created by Diana.Diaconu on 4/22/2015.
 */
public class DepartmentDao {

    EntityManager entityManager = new EntityManagerImpl();

    /**
     *
     * @param department
     * @return department object
     */
    public Department insertDepartment(Department department) {
        return (Department) entityManager.insert(department);
    }

    /**
     *
     * @param department
     * @return department object
     */
    public Department updateDepartment(Department department) {
        return entityManager.update(department);
    }

    /**
     *
     * @param department
     */
    public void deleteDepartment(Department department) {
        entityManager.delete(department);
    }

    /**
     *
     * @return a list of departments
     */
    public List<Department> getAllDepartments() {
        return entityManager.findAll(Department.class);
    }

    /**
     *
     * @param id
     * @return department object
     */
    public Department getDepartmentById(Integer id) {
        return entityManager.findById(Department.class, id);
    }

}
