package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.domain.Job;

import java.util.List;

/**
 * Created by Ionutz on 08.05.2015.
 */
public interface JobService {
    List<Job> findAllJobs();
    Job findOneJob(String id);
}
