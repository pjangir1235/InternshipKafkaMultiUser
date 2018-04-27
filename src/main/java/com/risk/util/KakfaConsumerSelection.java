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
//    configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //Figure out where to start processing messages from
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        new KafkaConsumer<Integer, JsonNode>(configProperties);
    kafkaConsumer.subscribe(
        Arrays.asList(topicName),
        new ConsumerRebalanceListener() {
          @Override
          public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            System.out.printf(
                "%s topic-partitions are revoked from this consumer\n",
                Arrays.toString(partitions.toArray()));
          }

          @Override
          public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            System.out.printf(
                "%s topic-partitions are assigned to this consumer\n",
                Arrays.toString(partitions.toArray()));
            Iterator<TopicPartition> topicPartitionIterator = partitions.iterator();
            while (topicPartitionIterator.hasNext()) {
              TopicPartition topicPartition = topicPartitionIterator.next();
              System.out.println(
                  "Current offset is "
                      + kafkaConsumer.position(topicPartition)+topicPartition.topic()
                      + " committed offset is ->"
                      + kafkaConsumer.committed(topicPartition));

              System.out.println("Resetting offset to " + startingOffset);
              kafkaConsumer.seek(topicPartition, startingOffset
            				  );
              System.out.println("Resetting offset to " + startingOffset);
            }
          }
        });
    return kafkaConsumer;
  }
}
