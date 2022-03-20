package com.base.ecommerce.job.log;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Component
public class JobRunningLogs {
    private static final Logger logger = Logger.getLogger(JobRunningLogs.class.getName());

    @Scheduled(fixedRate = 50000)
    public void log() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.warning("Job Running Logs: " + sdf.format(new java.util.Date()));
    }
}
