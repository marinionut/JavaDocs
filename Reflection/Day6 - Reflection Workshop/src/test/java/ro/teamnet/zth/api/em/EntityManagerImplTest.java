package ro.teamnet.zth.api.em;

import java.util.List;

import org.junit.Assert;
import ro.teamnet.zth.appl.domain.Department;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * Created by Ionutz on 04.05.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityManagerImplTest {

    EntityManagerImpl entityManager = new EntityManagerImpl();
    static Department dep = new Department();

    @Test
    public void aTestInsert() {
        dep.setDepartmentName("test unitar");
        dep = entityManager.insert(dep);

        assertEquals(entityManager.findById(Department.class, dep.getId()), dep);
    }

    @Test
    public void bTestUpdate() {
        Department d = entityManager.findById(Department.class, 30);
        d.setDepartmentName("departamnet test");
        entityManager.update(d);
        assertEquals(entityManager.findById(Department.class, 30).getDepartmentName(), d.getDepartmentName());
    }

    @Test
    public void cTestDelete() {
        entityManager.delete(dep);
        Department depById = entityManager.findById(Department.class, dep.getId());

        assertNull(depById);
    }

    @Test
    public void dFindAll() {
        List<Department> oldDeps = entityManager.findAll(Department.class);
        //add new dep
        dep.setDepartmentName("test unitar");
        dep = entityManager.insert(dep);
        List<Department> newDeps = entityManager.findAll(Department.class);

        assertEquals(oldDeps.size(), newDeps.size() - 1);
    }

    @Test
    public void eFindById() {
        EntityManager myEntityManager = new EntityManagerImpl();
        Department rezultat = myEntityManager.findById(Department.class, 10);

        Assert.assertNotNull(rezultat);
    }

}
