package com.base.ecommerce.job;

import com.base.ecommerce.core.utils.ContextAware;
import com.base.ecommerce.job.futureJob.BaseJob;
import com.base.ecommerce.job.futureJob.JobInstance;
import com.base.ecommerce.job.futureJob.model.Job;

public class ExecuteJobRunner implements Runnable {

    private final Job job;

    public ExecuteJobRunner(Job job) {
        this.job = job;
    }

    @Override
    public void run() {

        final BaseJob baseJob = (BaseJob) ContextAware.getBean(job.getServiceName().toString());

        baseJob.execute(job.getParams());

        final JobInstance jobInstance = ContextAware.getBean(JobInstance.class);
        jobInstance.save(job);
    }
}
