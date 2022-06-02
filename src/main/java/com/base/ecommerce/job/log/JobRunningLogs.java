package com.base.ecommerce.job.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class JobRunningLogs {
    private static final Logger logger = LogManager.getLogger(JobRunningLogs.class);

    @Scheduled(fixedRate = 100000)
    public void log() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Job Running Logs: " + sdf.format(new java.util.Date()));
    }
}
