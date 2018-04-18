package com.risk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.risk.constants.Urls;
import com.risk.models.Environment;
import com.risk.producer.dispatcher.EnvironmentDispatcher;
import com.risk.services.interfaces.EnvironmentService;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {


  @Autowired private EnvironmentDispatcher envDispatch;

  @Override
  public void getEnvironmentData(String stationCode) {
    RestTemplate rest = new RestTemplate();
    Environment env =
        rest.getForObject(Urls.ENVURLSTART + stationCode + Urls.ENVURLEND, Environment.class);
    envDispatch.dispatch(env);
  }
}
