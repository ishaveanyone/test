package com.dist;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Job2 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job2:"+LocalDateTime.now().toString());
    }
}
