package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Commonproblem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * 问题
   */
  private String problem;

  /**
   * 答案
   */
  private String answer;

  /**
   * 创建时间
   */

  @Column(name = "create_date")
  private Date createDate;

  /**
   * 用户点击查看次数
   */

  private Integer frequency;

  /**
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取问题
   *
   * @return problem - 问题
   */
  public String getProblem() {
    return problem;
  }

  /**
   * 设置问题
   *
   * @param problem 问题
   */
  public void setProblem(String problem) {
    this.problem = problem;
  }

  /**
   * 获取答案
   *
   * @return answer - 答案
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * 设置答案
   *
   * @param answer 答案
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * 获取创建时间
   *
   * @return create_date - 创建时间
   */
  public Date getCreateDate() {
    return createDate;
  }

  /**
   * 设置创建时间
   *
   * @param createDate 创建时间
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  /**
   * 获取用户点击查看次数
   *
   * @return frequency - 用户点击查看次数
   */
  public Integer getFrequency() {
    return frequency;
  }

  /**
   * 设置用户点击查看次数
   *
   * @param frequency 用户点击查看次数
   */
  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }
}
