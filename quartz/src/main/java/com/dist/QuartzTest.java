package com.dist;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.spi.JobStore;

import javax.swing.*;
import java.util.*;

import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;



public class QuartzTest {

    public static void main(String[] args) {

        try {
            //用于做日期排除
            HolidayCalendar cal = new HolidayCalendar();
            cal.addExcludedDate( new Date(2019,7,8));

            // 工厂模式 获取 scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.addCalendar("myHoliday", cal, true,true);

            MyJobListener myJobListener=new MyJobListener();
            scheduler.getListenerManager().addJobListener(myJobListener, jobGroupEquals("group1"));
            MySchedulerListener mySchedulerListener=new MySchedulerListener();
            scheduler.getListenerManager().addSchedulerListener(mySchedulerListener);
            // and start it off
            scheduler.start();
            //需要一个任务 以及一个触发
            JobDetail job1 = JobBuilder.newJob(HelloJob.class)
                    .usingJobData("name","hahh")
                    .withIdentity("job1", "group1")
                    .usingJobData("jobSays", "Hello World!")//可以绑定在运行期间的所需要的参数
                    .usingJobData("myFloatValue", 3.141f)
                    .build();

            JobDetail job2 = JobBuilder.newJob(Job2.class)
                    .usingJobData("name","hahh")
                    .withIdentity("job2", "group1")
                    .usingJobData("jobSays", "Hello World!")//可以绑定在运行期间的所需要的参数
                    .usingJobData("myFloatValue", 3.141f)

                    .build();
//            JDBCJobStore
            // 这个是一个作业触发器
            final Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData("tri","test")
                    .forJob("job1","group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(2).withRepeatCount(10)
                            )
                    .build();
            // 这个是一个作业触发器
            final Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group2")
                    .usingJobData("tri","test")
                    .forJob("job2","group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(5).withRepeatCount(10)
                    )
                   /*
                    * 如果多个trigger 同一个时刻 同时执行的 时候 priori 高的先执行
                    * Set the Trigger's priority.  When more than one Trigger have the same
                    * fire time, the scheduler will fire the one with the highest priority
                    * first.
                    * */
                    .withPriority(7)
                    .modifiedByCalendar("myHoliday") // but not on holidays
                    .build();
            Set<Trigger> triggers=new HashSet<Trigger>(){{
                add(trigger);add(trigger2);
            }};
            /**
             *
             * 如果 job 不唯一 那么 如果没有 flag 设置成 replace =true 会抛出异常
             * <p>If any of the given job or triggers already exist (or more
             * specifically, if the keys are not unique) and the replace
             * parameter is not set to true then an exception will be thrown.</p>
             */
            // Tell quartz to schedule the job using our trigger
            Map<JobDetail, Set<? extends Trigger>> triggersAndJobs =new HashMap<JobDetail, Set<? extends Trigger>>();
            triggersAndJobs.put(job1,new HashSet<Trigger>(){{add(trigger);}});
            triggersAndJobs.put(job2,new HashSet<Trigger>(){{add(trigger2);}});
            scheduler.scheduleJobs(triggersAndJobs,false);
//            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
