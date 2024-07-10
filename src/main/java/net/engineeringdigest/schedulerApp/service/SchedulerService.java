package net.engineeringdigest.schedulerApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.schedulerApp.entity.Job;
import net.engineeringdigest.schedulerApp.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    @Autowired
    private JobRepository jobRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduleFixedRateTask() {
        List<Job> pendingJobs = jobRepository.findByStatus("PENDING");
        for (Job job : pendingJobs) {
            processJob(job);
        }
    }

    private void processJob(Job job) {
        String threadName = Thread.currentThread().getName();
        logger.info("Processing job ID: {} on thread: {}", job.getId(), threadName);
        try {
            // Job processing logic here
            job.setStatus("COMPLETED");
        } catch (Exception e) {
            job.setStatus("FAILED");
            logger.error("Job ID: {} failed on thread: {}", job.getId(), threadName, e);
        } finally {
            jobRepository.save(job);
            logger.info("Job ID: {} processed with status: {} on thread: {}", job.getId(), job.getStatus(), threadName);
        }
    }


}
