package com.example.jaxb.reader;

import java.io.File;
import java.util.List;

import com.example.customer.Customer;
import com.example.customer.Customers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class ReadXMLWithJAXB {

	public static void main(String[] args) {
		Customers customers = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Customers.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			String resourcesDir = System.getProperty("user.dir") + "/src/main/resources";
			File file = new File(resourcesDir + "/customers.xml");
			
			customers = (Customers) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			System.out.println("JAXB, unmarshaller: " + e.getMessage());
		}
		List<Customer> data = customers.getCustomers();

		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
