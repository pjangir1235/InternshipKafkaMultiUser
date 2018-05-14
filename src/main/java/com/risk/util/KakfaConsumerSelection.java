package com.risk.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class KakfaConsumerSelection {

  public  KafkaConsumer<Integer, JsonNode> setKafka(
      String topicName, String groupId, long startingOffset) {

    Properties configProperties = new Properties();
    configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProperties.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.IntegerDeserializer");
    configProperties.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.connect.json.JsonDeserializer");
    configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    configProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        new KafkaConsumer<>(configProperties);
    kafkaConsumer.subscribe(
        Arrays.asList(topicName),
        new ConsumerRebalanceListener() {
          @Override
          public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                Arrays.toString(partitions.toArray());
          }

          @Override
          public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

                Arrays.toString(partitions.toArray());
            Iterator<TopicPartition> topicPartitionIterator = partitions.iterator();
            while (topicPartitionIterator.hasNext()) {
              TopicPartition topicPartition = topicPartitionIterator.next();
              kafkaConsumer.seek(topicPartition, startingOffset
            				  );
            }
          }
        });
    return kafkaConsumer;
  }
}
