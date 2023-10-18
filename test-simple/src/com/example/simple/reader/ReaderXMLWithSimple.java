package com.example.simple.reader;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.transform.RegistryMatcher;

import com.example.customer.Customer;
import com.example.customer.Customers;
import com.example.customer.DateTransform;

public class ReaderXMLWithSimple {
	
	public static void main(String[] args) {
		RegistryMatcher registryMatcher = new RegistryMatcher();
        registryMatcher.bind(Date.class, DateTransform.class);
        Strategy strategy = new TreeStrategy();	
		
		Serializer serializer = new Persister(strategy, registryMatcher);
		String resourcesDir = System.getProperty("user.dir") + "/resources";
		File file = new File(resourcesDir + "/customers.xml");
		
		Customers customers = null;
		try {
			customers = serializer.read(Customers.class, file);
		} catch (Exception e) {
			System.out.println("Serializer: " + e.getMessage());
		}
		List<Customer> data = customers.getCustomers();
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
