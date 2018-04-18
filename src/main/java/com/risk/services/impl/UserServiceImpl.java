package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.UserDispatcher;
import com.risk.producer.intefacerepo.UserRepo;
import com.risk.producer.model.User;
import com.risk.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepo userRepo;
  @Autowired UserDispatcher userDispatcher;

  @Override
  public void getUserData(String userName, String password) {
    Iterable<User> itr = userRepo.findByName(userName, password);
    Iterator<User> iter = itr.iterator();
    while (iter.hasNext()) userDispatcher.dispatch(iter.next());
  }
}
