package com.beiming.evidenceplatform.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * 作业调度控制器.
 *
 * @author zhangqi
 */
@Service
public class JobScheduleController {

  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;

  /**
   * 调度作业.
   *
   * @param cron CRON表达式
   */
  public void scheduleJob(String xmlid, String cron, Class<? extends Job> jobClass)
      throws SchedulerException {
    JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(xmlid).build();
    if (!getScheduler().checkExists(jobDetail.getKey())) {
      getScheduler().scheduleJob(jobDetail, createTrigger(xmlid, cron));
    }
    if (!getScheduler().isStarted()) {
      getScheduler().start();
    }
  }

  /**
   * 删除作业
   */
  public void delscheduleJob(String xmlid) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(xmlid);
    if (getScheduler().checkExists(jobKey)) {
      getScheduler().deleteJob(jobKey);
    }
  }

  /**
   * 重新调度作业.
   *
   * @param cron CRON表达式
   */
  public void rescheduleJob(String xmlid, String cron) throws SchedulerException {
    CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(TriggerKey.triggerKey(xmlid));
    if (!getScheduler().isShutdown() && null != trigger && !cron
        .equals(trigger.getCronExpression())) {
      getScheduler().rescheduleJob(TriggerKey.triggerKey(xmlid), createTrigger(xmlid, cron));
    } else if (!getScheduler().isShutdown() && null == trigger) {
      this.scheduleJob(xmlid, cron, PayCheckJob.class);
    }
  }

  private CronTrigger createTrigger(String xmlid, String cron) {
    return TriggerBuilder.newTrigger().withIdentity(xmlid).withSchedule(
        CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing()).build();
  }

  /**
   * 暂停作业.
   */
  public void pauseJob() throws SchedulerException {
    if (!getScheduler().isShutdown()) {
      getScheduler().pauseAll();
    }
  }

  /**
   * 恢复作业.
   */
  public void resumeJob() throws SchedulerException {
    if (!getScheduler().isShutdown()) {
      getScheduler().resumeAll();
    }
  }

  /**
   * 立刻启动作业.
   */
  public void triggerJob(String xmlid) throws SchedulerException {
    if (!getScheduler().isShutdown()) {
      getScheduler().triggerJob(JobKey.jobKey(xmlid));
    }
  }

  /**
   * 关闭调度器.
   */
  public void shutdown() throws SchedulerException {
    if (!getScheduler().isShutdown()) {
      getScheduler().shutdown();
    }
  }

  public Scheduler getScheduler() {
    return schedulerFactoryBean.getScheduler();
  }
}
