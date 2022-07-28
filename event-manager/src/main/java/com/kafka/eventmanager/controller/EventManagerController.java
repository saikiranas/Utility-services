package com.kafka.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventManagerController {
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.topicName1}")
	private String topicName1;
	
	@Value("${spring.kafka.topicName2}")
	private String topicName2;
	
	
	@PostMapping("event-manager/send")
	public void sendMessage(@RequestParam(name = "message") String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName1, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + message + 
			              "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Message sending failed : "+ex.getMessage());
			}
		});
		
		ListenableFuture<SendResult<String, String>> future2 = kafkaTemplate.send(topicName2, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + message + 
			              "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Message sending failed : "+ex.getMessage());
			}
		});
	}
	
	@GetMapping
	public String test() {
		return "Test";
	}

}
