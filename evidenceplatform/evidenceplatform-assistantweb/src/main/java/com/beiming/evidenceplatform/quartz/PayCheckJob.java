package com.beiming.evidenceplatform.quartz;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author zhangqi
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PayCheckJob extends QuartzJobBean {

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext)
      throws JobExecutionException {

  }
}