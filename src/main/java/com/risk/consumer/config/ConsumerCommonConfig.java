package com.risk.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class ConsumerCommonConfig {

  @Value("${kafka.bootstrap}")
  private String bootstrap;

  @Value("${consumer-enable-auto-commit}")
  private Boolean enableCommit;

  @Value("${consumer-auto-commit-interval}")
  private String commitIntervalValue;

  @Value("${consumer-session-timeout}")
  private String sessionTimeOut;

  @Value("${consumer-auto-offset}")
  private String consumerAutoOffset;

  @Value("${consumer-reconnect-backoff}")
  private String reconnectBackoff;

  public Map<String, Object> commonProperty() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableCommit);
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitIntervalValue);
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeOut);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffset);
    props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, reconnectBackoff);
    return props;
  }
}
