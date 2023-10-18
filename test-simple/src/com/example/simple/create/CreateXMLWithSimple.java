package com.example.simple.create;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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

public class CreateXMLWithSimple {

	public static void main(String[] args) {
		List<Customer> data = new ArrayList<>();
		getDataCustomer(data);   
		
		Customers customers = new Customers();
		customers.setCustomers(data);
		
		RegistryMatcher registryMatcher = new RegistryMatcher();
        registryMatcher.bind(Date.class, DateTransform.class);
        Strategy strategy = new TreeStrategy();		
		
		Serializer serializer = new Persister(strategy, registryMatcher);
		StringWriter writer = new StringWriter();
		
		try {
			serializer.write(customers, writer);
		} catch (Exception e) {
			System.out.println("Serializer writer: " + e.getMessage());
		}
		System.out.println(writer);
		
		String resourcesDir = System.getProperty("user.dir") + "/resources";
		File file = new File(resourcesDir + "/customers.xml");
		
		try {
			serializer.write(customers, file);
		} catch (Exception e) {
			System.out.println("Serializer file: " + e.getMessage());
		}

	}
	
	private static void getDataCustomer(List<Customer> data) {		
        // Добавляем несколько объектов Customer в список
        data.add(new Customer(1, "John Dol", "123-456-7890", "Description 1", 30, BigDecimal.valueOf(1000.00), true, new Date()));
        data.add(new Customer(2, "Jane Smith", "987-654-3210", "Description 2", 25, BigDecimal.valueOf(2000.00), false, new Date()));
        data.add(new Customer(3, "Bamb Smoot", "987-654-0000", "Description 3", 27, BigDecimal.valueOf(5500.00), true, new Date()));
	}

}
