package com.base.ecommerce.job.futureJob;

import com.base.ecommerce.job.futureJob.model.Job;
import com.base.ecommerce.job.futureJob.model.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JobInstance {

    private final Logger logger = LoggerFactory.getLogger(JobInstance.class);

    private final JobRepository jobRepository;

    public JobInstance(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void save(Job job) {
        try {
            jobRepository.save(job);
        }catch (Exception e) {
            logger.info("Job save error");
        }
    }

    public void delete(Job job) {
        jobRepository.delete(job);
    }

    public Job findByJobId(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
