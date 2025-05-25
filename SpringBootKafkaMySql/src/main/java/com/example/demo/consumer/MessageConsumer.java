package com.example.demo.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
@Component
@EnableKafka
public class MessageConsumer {
	  private Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	
   @Autowired
   private EmployeeRepository repo;
    
      @KafkaListener(topics = "${myapp.kafka.topic}",groupId = "group1")
      public void consume(String employee) {
         log.info("MESSAGE RECEIVED AT CONSUMER END -> " + employee);
        // empRepo.save(employee);
         
         try {
        	String passjson=employee.replaceAll("\"", "");
 			JSONObject jsonObject = stringToJsonObject(passjson);
 			System.out.println(jsonObject.toString()); // Use toString(int indentFactor) for pretty printing
 			System.out.println(jsonObject.get("id"));
 			System.out.println(jsonObject.get("name"));
 			
 			Employee e1=new Employee();
 			int id=(int) jsonObject.get("id");
 			String s1=(String) jsonObject.get("name");
 			e1.setId(id);
 			e1.setName(s1);
 			repo.save(e1);
 			

 		} catch (JSONException e) {
 			log.error("Error parsing JSON string: ",e.getMessage());
 		}

      }
      
      public static JSONObject stringToJsonObject(String jsonString) throws JSONException {
  		return new JSONObject(jsonString);
  	}
}
