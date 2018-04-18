package com.risk.consumer.model;

public class UserDTO {

  private int userId;

  private String userName;
  private String passwrd;

  public UserDTO() {
    super();
  }

  public String getPassword() {
    return passwrd;
  }

  public int getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setPassword(String passwrd) {
    this.passwrd = passwrd;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
