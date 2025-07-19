package com.example.demo.config;

import java.util.HashMap;
import java.util.List;
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

@Configuration
public class KafkaConfig {
	@Bean
	public ProducerFactory<String, Employee> producerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public ConsumerFactory<String, Employee> consumerFactory() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	    props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false); // Optional

	    return new DefaultKafkaConsumerFactory<>(props);
	}

	

	@Bean
	public ProducerFactory<String, String> stringProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> producerFactory) {
		return new KafkaTemplate<>(stringProducerFactory());
	}

	@Bean
	public KafkaTemplate<String, Employee> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ConsumerFactory<String, String> stringConsumerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "groupid1");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<String, String>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(stringConsumerFactory());
		return factory;
	}



	@Bean
	public KafkaTemplate<String, List<Employee>> employeeBatchKafkaTemplate() {
		return new KafkaTemplate<>(employeeBatchProducerFactory());
	}

	@Bean
	public ProducerFactory<String, List<Employee>> employeeBatchProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public ConsumerFactory<String, List<Employee>> employeeBatchConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-two");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

	    // Manually configure the deserializer with TypeReference
	    JsonDeserializer<List<Employee>> valueDeserializer =
	        new JsonDeserializer<>(new com.fasterxml.jackson.core.type.TypeReference<List<Employee>>() {});
	    valueDeserializer.addTrustedPackages("*");
	    valueDeserializer.setUseTypeMapperForKey(false);

	    return new DefaultKafkaConsumerFactory<>(
	        props,
	        new StringDeserializer(),
	        valueDeserializer
	    );
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, List<Employee>> employeeBatchKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, List<Employee>> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(employeeBatchConsumerFactory());
		return factory;
	}

}
