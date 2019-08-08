package com.dist;


import org.quartz.*;

public class MySchedulerListener implements SchedulerListener {
    public void jobScheduled(Trigger trigger) {
        System.out.println("a1");
    }

    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("a2");

    }

    public void triggerFinalized(Trigger trigger) {
        System.out.println("a3");

    }

    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("a4");

    }

    public void triggersPaused(String triggerGroup) {
        System.out.println("a5");

    }

    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("a6");

    }

    public void triggersResumed(String triggerGroup) {

    }

    public void jobAdded(JobDetail jobDetail) {

    }

    public void jobDeleted(JobKey jobKey) {

    }

    public void jobPaused(JobKey jobKey) {

    }

    public void jobsPaused(String jobGroup) {

    }

    public void jobResumed(JobKey jobKey) {

    }

    public void jobsResumed(String jobGroup) {

    }

    public void schedulerError(String msg, SchedulerException cause) {

    }

    public void schedulerInStandbyMode() {

    }

    public void schedulerStarted() {

    }

    public void schedulerStarting() {

    }

    public void schedulerShutdown() {

    }

    public void schedulerShuttingdown() {

    }

    public void schedulingDataCleared() {

    }
}
