package com.beiming.evidenceplatform.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成唯一的案件编号
 *
 * @author tp Date : 2018/6/6/006 9:55
 */
public class GlobalUtil {

  protected static final Logger LOG = LoggerFactory.getLogger(GlobalUtil.class);

  private long workerId;
  private long datacenterId;
  private long sequence = 0L;

  private long twepoch = 1288834974657L;

  private long workerIdBits = 5L;
  private long datacenterIdBits = 5L;
  private long maxWorkerId = -1L ^ (-1L << workerIdBits);
  private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  private long sequenceBits = 12L;

  private long workerIdShift = sequenceBits;
  private long datacenterIdShift = sequenceBits + workerIdBits;
  private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  private long sequenceMask = -1L ^ (-1L << sequenceBits);

  private long lastTimestamp = -1L;

  public static GlobalUtil worker2;

  static {
    worker2 = new GlobalUtil(0, 1);
  }
  public static String getGlobalId() {
    Long id = worker2.nextId();
    return id.toString();

  }

  public GlobalUtil(long workerId, long datacenterId) {
    // sanity check for workerId
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(
          String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
    LOG.info(String.format(
        "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
        timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
  }

  public static GlobalUtil getInstance() {
    worker2 = new GlobalUtil(0, 1);
    return worker2;
  }

  public synchronized long nextId() {
    long timestamp = timeGen();

    if (timestamp < lastTimestamp) {
      LOG.error(
          String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
      throw new RuntimeException(String
          .format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }

    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = timestamp;

    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (
        workerId << workerIdShift) | sequence;
  }

  protected long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  protected long timeGen() {
    return System.currentTimeMillis();
  }

  public static void main(String[] args) {
    System.out.println(getGlobalId());
  }


}
