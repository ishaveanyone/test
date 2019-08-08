package com.dist;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;


public class MyJobListener implements JobListener {
    public String getName() {
        return "test";
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("listen>>>>>>"+1);
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("listen>>>>>>"+2);
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("listen>>>>>>"+3);
    }
}
