





























package org.spring.boot.rest.controller;

import org.spring.boot.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
//	@Autowired
//	KafkaTemplate<String, String> kafkaTemplate;
	
//	@PostMapping(value="/addcustomers",consumes = {"application/json"})
//	public String addCustomer(@RequestBody List<Customer> customerList) {
//		System.out.println("****************** customerList*****"+customerList);
//		return customerService.addCustomers(customerList);
//	}
	
	@PostMapping(value = "/save/users",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = "application/json")
	public ResponseEntity saveUsers(@RequestParam("files")MultipartFile[] file) throws Exception {
		
		for (MultipartFile multipartFile : file) {
			customerService.sendToKafka(multipartFile);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
//	@GetMapping("/publish/{name}")
//	public String post(@PathVariable final String name) {
//		
//		kafkaTemplate.send(KafkaConstants.TOPIC,"hii "+name+" welcome to our new future");
//		System.out.println("****************** customerList*****"+name);
//		return "published successfully";
//	}
}
