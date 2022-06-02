package com.base.ecommerce.job.futureJob.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "job_future")
public class Job {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private String jobDescription;
    private Long jobDate;
    private String jobTime;
    private String jobLocation;
    private String serviceName;
    @Transient
    private Map<String, Object> params;

    public Long getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public Long getJobDate() {
        return jobDate;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public Object getServiceName() {
        return serviceName;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public static final class Builder {
        private Long id;
        private String jobName;
        private String jobDescription;
        private Long jobDate;
        private String jobTime;
        private String jobLocation;
        private String serviceName;
        private Map<String, Object> params;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder jobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        public Builder jobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
            return this;
        }

        public Builder jobDate(Long jobDate) {
            this.jobDate = jobDate;
            return this;
        }

        public Builder jobTime(String jobTime) {
            this.jobTime = jobTime;
            return this;
        }

        public Builder jobLocation(String jobLocation) {
            this.jobLocation = jobLocation;
            return this;
        }

        public Builder serviceName(String name) {
            this.serviceName = name;
            return this;
        }

        public Builder params( Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Job build() {
            Job job = new Job();
            job.setJobTime(jobTime);
            job.jobLocation = this.jobLocation;
            job.jobName = this.jobName;
            job.jobDate = this.jobDate;
            job.jobDescription = this.jobDescription;
            job.id = this.id;
            job.serviceName = this.serviceName;
            job.params = this.params;
            return job;
        }
    }
}

