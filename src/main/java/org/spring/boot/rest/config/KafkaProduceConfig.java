package org.spring.boot.rest.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.spring.boot.rest.model.Customer;
import org.spring.boot.rest.util.KafkaConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class KafkaProduceConfig {

	@Bean
	public KafkaTemplate<String, Customer> kafkaTemplate() {
		
		return new KafkaTemplate<>(producerFactoryConfig());
	}
	

	@Bean
	public ProducerFactory<String, Customer> producerFactoryConfig() {
		
		Map<String, Object> propsConfig = new HashMap<>();
		
		propsConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);
		propsConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		propsConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(propsConfig);
	}
	
//	@Bean
//	public KafkaTemplate<String, String> kafkaTemplateString() {
//		
//		return new KafkaTemplate<>(producerFactoryConfigString());
//	}
//
//	@Bean
//	public ProducerFactory<String, String> producerFactoryConfigString() {
//		
//		Map<String, Object> propsConfig = new HashMap<>();
//		
//		propsConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);
//		propsConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		propsConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		
//		return new DefaultKafkaProducerFactory<>(propsConfig);
//	}
}
