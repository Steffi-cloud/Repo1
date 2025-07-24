package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEvents;
@Configuration
public class KafkaConfig {
	
		
		
		@Bean
		public ProducerFactory<String, EmployeeEvents> employeeEventsProducerFactory() {
			Map<String, Object> config = new HashMap<>();
			config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
			config.put(JsonSerializer.TYPE_MAPPINGS, "employeeEvents:com.example.demo.model.EmployeeEvents");
			//config.put(JsonSerializer.DEFAULT_TYPE, "com.example.demo.model.EmployeeEvents");
			config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
			return new DefaultKafkaProducerFactory<>(config);
		}

		@Bean
		public ConsumerFactory<String, EmployeeEvents> employeeEventsConsumerFactory() {
		    Map<String, Object> props = new HashMap<>();
		    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
		    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		    props.put(JsonSerializer.TYPE_MAPPINGS, "employeeEvents:com.example.demo.model.EmployeeEvents");
		 //   props.put(JsonSerializer., "com.example.demo.model.EmployeeEvents");

		    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		    props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false); // Optional

		    return new DefaultKafkaConsumerFactory<>(props);
		}
		
		
		

		
		@Bean
		public KafkaTemplate<String, EmployeeEvents> employeeEventsKafkaTemplate() {
			return new KafkaTemplate<>(employeeEventsProducerFactory());
		}
		
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, EmployeeEvents> employeeEventsKafkasListenerContainerFactory() {
			ConcurrentKafkaListenerContainerFactory<String, EmployeeEvents> factory = new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(employeeEventsConsumerFactory());
			return factory;
		}

	

}
