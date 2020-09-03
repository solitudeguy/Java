package com.sourav.kafka.consumer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sourav.kafka.consumer.model.Employee;

@RestController
public class KafkaController {
	 
	    @Autowired
	    private Gson gson;
	 
	    @KafkaListener(topics = { "employee" })
	    public void getTopics(@RequestBody String emp) {
	        System.out.println("Kafka event consumed is: " + emp);
	        Employee model = gson.fromJson(emp, Employee.class);
	        System.out.println("Model converted value: " + model.toString());
	    }
	}

