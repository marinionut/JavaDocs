package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Ionutz on 05.05.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
    static Department department = new Department();
    DepartmentDao departmentDao = new DepartmentDao();

    @Test
    public void aTestInsertDepartment() {
        department.setDepartmentName("testIT");
        department.setLocation(1300);
        department = departmentDao.insertDepartment(department);

        Assert.assertEquals(departmentDao.getDepartmentById(department.getId()), department);
    }

    @Test
    public void bTestUpdateDepartment() {
        EntityManager entityManager = new EntityManagerImpl();
        department = entityManager.findById(Department.class, 120);
        department.setDepartmentName("test dep name");
        department = departmentDao.updateDepartment(department);
        Assert.assertEquals(departmentDao.getDepartmentById(department.getId()), department);
    }

    @Test
    public void cTestDeleteDepartment() {
        departmentDao.deleteDepartment(department);
        Department depById = departmentDao.getDepartmentById(department.getId());

        Assert.assertNull(depById);
    }

    @Test
    public void dGetAllDepartments() {
        List<Department> oldDep = departmentDao.getAllDepartments();
        //add new department
        department.setDepartmentName("testIT2");
        department.setLocation(1200);
        departmentDao.insertDepartment(department);
        List<Department> newDep = departmentDao.getAllDepartments();

        Assert.assertEquals(oldDep.size(), newDep.size() - 1);
    }
}
