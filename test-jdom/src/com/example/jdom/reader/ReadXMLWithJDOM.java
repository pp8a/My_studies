package com.example.jdom.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.example.customer.Customer;

public class ReadXMLWithJDOM {
	
	public static void main(String[] args) {
		
		String dir = System.getProperty("user.dir");
		String filename = "/output/customers.xml";
		
		JDOMReader reader = new JDOMReader();
		List<Customer> data = reader.getDataFromXML(dir+filename, "");
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
		
		System.out.println("---------------------------------------");
		
		//reading with filter
		String filter = "//xsi:customer[xsi:age>25]";		
		data = reader.getDataFromXML(dir+filename, filter);
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
