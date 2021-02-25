package org.spring.boot.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.spring.boot.rest.model.Customer;
import org.spring.boot.rest.util.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService {

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;

//	public String addCustomers(List<Customer> customerList) {
//
//		if (!customerList.isEmpty()) {
//			for (Customer customer : customerList) {
//				kafkaTemplate.send(KafkaConstants.TOPIC, customer);
//				System.out.println("**********Message published to kafka topic*************");
//			}
//		}
//		return "Customer record is added to kafka queue successfully";
//	}

	public String sendToKafka(MultipartFile file) throws Exception {

		long startTime = System.currentTimeMillis();
		Integer count = 0;
		List<Customer> customers = parseCsvFile(file);

		for (Customer customer : customers) {
			kafkaTemplate.send(KafkaConstants.TOPIC, customer);
			System.out.println("**********Message published to kafka topic*************");
			count++;
		}

		long endTime = System.currentTimeMillis();
		System.out.println("users total count :" + count);
		System.out.println("Total time taken :" + (endTime - startTime));

		return "users list added to topic :";
	}

	public List<Customer> parseCsvFile(MultipartFile file) throws Exception {

		List<Customer> customers = new ArrayList<>();
		if (file != null) {

			try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));) {

				String line;
				while ((line = br.readLine()) != null) {
					String[] customerData = line.split(",");
					Customer customer = new Customer();
					customer.setCid(Integer.parseInt(customerData[0]));
					customer.setName(customerData[1]);
					customer.setCity(customerData[2]);

					customers.add(customer);
				}

			} catch (IOException e) {
				System.out.println("csv file is failed to parse {} ");
				throw new Exception("failed to parse csv faile", e);
			}
		}
		return customers;
	}
}
