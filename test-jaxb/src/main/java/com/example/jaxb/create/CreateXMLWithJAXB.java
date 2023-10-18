package com.example.jaxb.create;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.customer.Customer;
import com.example.customer.Customers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class CreateXMLWithJAXB {

	public static void main(String[] args) {
		List<Customer> data = new ArrayList<>();
		getDataCustomer(data);        
        
        Customers customers = new Customers();        
		customers.setCustomers(data);        
        
		try {
			JAXBContext  context = JAXBContext.newInstance(Customers.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//view XML
			
			StringWriter writer = new StringWriter();
			marshaller.marshal(customers, writer);
			
			String resourcesDir = System.getProperty("user.dir") + "/src/main/resources";
			File file = new File(resourcesDir + "/customers.xml");
			marshaller.marshal(customers, file);
			
			System.out.println(writer);
		} catch (JAXBException e) {
			System.out.println("JAXB: " + e.getMessage());
		}
        
	}

	private static void getDataCustomer(List<Customer> data) {		
        // Добавляем несколько объектов Customer в список
        data.add(new Customer(1, "John Dol", "123-456-7890", "Description 1", 30, BigDecimal.valueOf(1000.00), true, new Date()));
        data.add(new Customer(2, "Jane Smith", "987-654-3210", "Description 2", 25, BigDecimal.valueOf(2000.00), false, new Date()));
        data.add(new Customer(3, "Bamb Smoot", "987-654-0000", "Description 3", 27, BigDecimal.valueOf(5500.00), true, new Date()));
	}

}
