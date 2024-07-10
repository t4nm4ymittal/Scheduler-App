package net.engineeringdigest.schedulerApp.controller;


import lombok.RequiredArgsConstructor;
import net.engineeringdigest.schedulerApp.entity.Job;
import net.engineeringdigest.schedulerApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Validated
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public void submitJob(@RequestBody Job job) {
        jobService.submitJob(job);
    }

    @GetMapping("/{id}")
    public Job getJobStatus(@PathVariable Long id) {
        return jobService.getJobStatus(id);
    }

}
