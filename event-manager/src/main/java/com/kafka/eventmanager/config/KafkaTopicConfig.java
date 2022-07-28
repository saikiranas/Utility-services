package com.kafka.eventmanager.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
	
	
	@Value("${spring.kafka.bootstrapservers}")
	private String kafkaBootstrapServers;
	
	@Value("${spring.kafka.topicName1}")
	private String topicName1;
	
	@Value("${spring.kafka.topicName2}")
	private String topicName2;
	
	
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> config = new HashMap<>();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
		return new KafkaAdmin(config);
	}
	
	@Bean
	public NewTopic pubTopic1() {
		return new NewTopic(topicName1, 1, (short) 1);
	}
	
	@Bean
	public NewTopic pubTopic2() {
		return new NewTopic(topicName2, 1, (short) 1);
	}

}
