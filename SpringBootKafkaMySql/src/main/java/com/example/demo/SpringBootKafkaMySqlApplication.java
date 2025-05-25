package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.demo.model.Employee;

@SpringBootApplication

public class SpringBootKafkaMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaMySqlApplication.class, args);
	}

	
	@Bean 
	public KafkaTemplate<String,String> kafkaTemplate(){
		return new KafkaTemplate<String, String>(this.kafkaProducerFactory());
	}
	
	  @Bean
	  public Map<String, Object> consumerConfig() {
	      final HashMap<String, Object> result = new HashMap<>();
	      result.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	      result.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      result.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      return result;
	  }
	  
	  
	  @Bean
		public Map<String, Object> producerConfig() {
			final HashMap<String, Object> result = new HashMap<>();
			result.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			result.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			result.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
			return result;
		}

	
	
	
	
	  
	  @Bean
		@ConditionalOnMissingBean(ProducerFactory.class)
		public ProducerFactory<String, String> kafkaProducerFactory() {
			return new DefaultKafkaProducerFactory<String, String>(producerConfig());
		}
}
