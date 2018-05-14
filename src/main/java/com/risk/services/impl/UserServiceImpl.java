package com.risk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.intefacerepo.UserRepo;
import com.risk.producer.model.User;
import com.risk.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepo userRepo;

  @Override
  public boolean getUserData(String userName, String password) {
    User user;
    boolean status;
    user = userRepo.findByName(userName, password);
    if (user.getUserName().equals(userName) && user.getPassword().equals(password)) status = true;
    else status = false;
    return status;
  }
}
