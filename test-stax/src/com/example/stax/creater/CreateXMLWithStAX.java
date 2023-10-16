package com.example.stax.creater;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.silentsoft.stopwatch.Stopwatch;

import com.example.customer.Customer;

public class CreateXMLWithStAX {

	public static void main(String[] args) {
		// Создаем список Customers
        List<Customer> customerList = new ArrayList<>();
        // Добавляем несколько объектов Customer в список
        customerList.add(new Customer(1, "John Dol", "123-456-7890", "Description 1", 30, BigDecimal.valueOf(1000.00), true, new Date()));
        customerList.add(new Customer(2, "Jane Smith", "987-654-3210", "Description 2", 25, BigDecimal.valueOf(2000.00), false, new Date()));
        customerList.add(new Customer(3, "Bamb Smoot", "987-654-0000", "Description 3", 27, BigDecimal.valueOf(5500.00), true, new Date()));
        
        File directory = createDirectory();        
        
        StAXStreamCreater creater = new StAXStreamCreater();
        creater.createDocument(customerList, directory + "/customers.xml");     

	}
	
	private static File createDirectory() {
		String dir = System.getProperty("user.dir");
        File directory = new File(dir+"/output");
        if(!directory.exists()) {
        	 boolean created = directory.mkdir();
        	    if (created) {
        	    	System.out.println("Ok! The directory has been created.");
        	    }else {
        	    	System.out.println("Directory not found");
        	    }
        }
		return directory;
	}

}
