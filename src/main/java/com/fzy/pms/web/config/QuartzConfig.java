package com.fzy.pms.web.config;

import com.fzy.pms.service.job.HelloWordJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: QuartzConfig
 * @description:
 * @author: fzy
 * @date: 2019/06/13 11:13:47
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail helloJobDetail(){
        return JobBuilder.newJob(HelloWordJob.class)
                .withIdentity("myJob","myGroup")
                .usingJobData("","")
                .storeDurably()
                .build();
    }

    //@Bean
    public Trigger helloJobTrigger(){
        return TriggerBuilder.newTrigger().forJob(helloJobDetail())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("1/5 * * * * ?"))
                .build();
    }

}
