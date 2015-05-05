package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.JobsDao;
import ro.teamnet.zth.appl.domain.Jobs;

import java.util.List;

/**
 * Created by Ionutz on 05.05.2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobsDaoTest {
    static Jobs job = new Jobs();
    JobsDao jobDao = new JobsDao();

    @Test
    public void aTestInsertJob() {
        job.setId("HR - teesttt2 ");
        job.setJobTitle("Junior Developer - test");
        job.setMaxSalary("2500");
        job.setMinSalary("1500");
        job = jobDao.insertJob(job);

        Assert.assertEquals(jobDao.getJobById(job.getId()), job);
    }

    @Test
    public void bTestUpdateJob() {
        EntityManager entityManager = new EntityManagerImpl();
        job = entityManager.findById(Jobs.class, "SA_MAN");
        job.setJobTitle("Junior Developer - test2");
        job = jobDao.updateJob(job);
        Assert.assertEquals(jobDao.getJobById(job.getId()), job);
    }

    @Test
    public void cTestDeleteJob() {
        jobDao.deleteJob(job);
        Jobs jobById = jobDao.getJobById(job.getId());

        Assert.assertNull(jobById);
    }

    @Test
    public void dGetAllJobs() {
        List<Jobs> oldJob = jobDao.getAllJobs();
        //add new job
        job.setId("HR - test 8");
        job.setJobTitle("Junior Developer-test 3");
        job.setMaxSalary("2500");
        job.setMinSalary("1500");
        jobDao.insertJob(job);
        List<Jobs> newJob = jobDao.getAllJobs();

        Assert.assertEquals(oldJob.size(), newJob.size() - 1);
    }
}
