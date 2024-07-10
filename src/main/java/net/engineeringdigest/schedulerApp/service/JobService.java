package net.engineeringdigest.schedulerApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.schedulerApp.entity.Job;
import net.engineeringdigest.schedulerApp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

@Component
public class JobService {
    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public void submitJob(Job job){
        jobRepository.save(job);
    }


    public Job getJobStatus(Long id){
        return jobRepository.findById(id).orElse(null);
    }




}
