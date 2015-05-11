package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.dao.JobDao;
import ro.teamnet.zth.app.domain.Job;

import java.util.List;

/**
 * Created by Ionutz on 08.05.2015.
 */
public class JobServiceImpl implements JobService{

    @Override
    public List<Job> findAllEmployees() {
        JobDao employeeDao = new JobDao();
        return employeeDao.getAllJobs();
    }

    @Override
    public Job findOneEmployee(Integer id) {
        JobDao employeeDao = new JobDao();
        return employeeDao.getJobById(id);
    }

}
