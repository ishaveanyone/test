package com.dist;

import org.quartz.*;

import javax.naming.Name;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class HelloJob implements Job {
    //放在 这里 使用 using data 自动 会进行映射
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobkey = jobExecutionContext.getJobDetail().getKey();
        TriggerKey triggerkey = jobExecutionContext.getTrigger().getKey();

//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap dataMap =  jobExecutionContext.getMergedJobDataMap();//JobDetail 以及trigger 里面有各自的datamap 使用该方法进行合并

        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloat("myFloatValue");
        String tri=dataMap.getString("tri");

        System.err.println("Instance " + jobkey+"-"+triggerkey + " of DumbJob says: " +
                jobSays + ", and val is: " + myFloatValue+" name:"+name+ " tri:"+tri);
        String[] strs={"1","2"};
            String.join(",",strs);

        Arrays.asList();
    }


    public static void main(String[] args) {


        Random rand = new Random();
        System.out.println(rand.nextInt(1));
    }
}
