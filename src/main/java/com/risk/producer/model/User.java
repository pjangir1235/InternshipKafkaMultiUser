package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;

  private String userName;
  private String passwrd;

  public User() {
    super();
  }

  public String getPassword() {
    return passwrd;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setPassword(String passwrd) {
    this.passwrd = passwrd;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
