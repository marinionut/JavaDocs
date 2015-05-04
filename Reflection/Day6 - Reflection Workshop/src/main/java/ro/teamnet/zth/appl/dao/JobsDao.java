package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Jobs;

import java.util.List;

/**
 * Created by Ionutz on 04.05.2015.
 */
public class JobsDao {
    EntityManager entityManager = new EntityManagerImpl();

    public Jobs insertJob(Jobs jobs) {
        return (Jobs) entityManager.insert(jobs);
    }

    public Jobs updateJob(Jobs jobs) {
        return entityManager.update(jobs);
    }


    public void deleteJob(Jobs jobs) {
        entityManager.delete(jobs);
    }


    public List<Jobs> getAllJobs() {
        EntityManager entityManager = new EntityManagerImpl();
        return entityManager.findAll(Jobs.class);
    }


    public Jobs getJobById(Object id) {
        return entityManager.findById(Jobs.class, id);
    }
}
