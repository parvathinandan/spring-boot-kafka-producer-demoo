package org.spring.boot.rest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Customer implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private String name;
	private String city;
}
