package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Ionutz on 29.04.2015.
 */
@Table(name = "jobs")
public class Jobs {
    @Id(name = "JOB_ID")
    private String id;
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "MIN_SALARY")
    private String minSalary;
    @Column(name = "MAX_SALARY")
    private String maxSalary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "id='" + id + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs jobs = (Jobs) o;

        if (id != null ? !id.equals(jobs.id) : jobs.id != null) return false;
        if (jobTitle != null ? !jobTitle.equals(jobs.jobTitle) : jobs.jobTitle != null) return false;
        if (minSalary != null ? !minSalary.equals(jobs.minSalary) : jobs.minSalary != null) return false;
        return !(maxSalary != null ? !maxSalary.equals(jobs.maxSalary) : jobs.maxSalary != null);

    }
}
