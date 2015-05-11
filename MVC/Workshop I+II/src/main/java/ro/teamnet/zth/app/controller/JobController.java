package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParameters;
import ro.teamnet.zth.app.service.JobService;
import ro.teamnet.zth.app.service.JobServiceImpl;

/**
 * Created by Ionutz on 07.05.2015.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    @MyRequestMethod(urlPath = "/all")
    public String getAllJobs() {
        JobService jobService = new JobServiceImpl();
        return String.valueOf(jobService.findAllJobs());
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneJob(@MyRequestParameters(paramName = "idJob", paramType = String.class)String idJob) {
        JobService jobService = new JobServiceImpl();
        return jobService.findOneJob(idJob);
    }
}
