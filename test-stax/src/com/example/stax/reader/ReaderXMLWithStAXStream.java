package com.example.stax.reader;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.example.customer.Customer;

public class ReaderXMLWithStAXStream {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
//		StAXStreamReader reader = new StAXStreamReader();
		StAXEventReader reader = new StAXEventReader();
		
		String dir = System.getProperty("user.dir");
		String filename = "/output/customers.xml";
		List<Customer> data = reader.getDataFromXML(dir + filename);
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
		
		System.out.println("Data returned " + data.size() + " customers.");

	}

}
