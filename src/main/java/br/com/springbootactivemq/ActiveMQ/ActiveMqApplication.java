package br.com.springbootactivemq.ActiveMQ;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqApplication.class, args);
	}
	
	@Bean
	public JobDetail sampleJobDetail() {
		return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
				.usingJobData("name", "World").storeDurably().build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(2).repeatForever();

		return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
				.withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
	}
}
