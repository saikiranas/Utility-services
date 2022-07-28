package com.kafka.eventmanager.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerImpl {
	
	@KafkaListener(topics = "${spring.kafka.topicName1}, ${spring.kafka.topicName2}", groupId = "${spring.kafka.groupId}")
	public void listenMessages(String message) {
		System.out.println("Message recieved from Kafka Topic is : "+message);
	}

}
