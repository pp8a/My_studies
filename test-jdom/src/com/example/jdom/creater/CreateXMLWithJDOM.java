package com.example.jdom.creater;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.example.customer.Customer;

public class CreateXMLWithJDOM {
	public static void main(String[] args) {
		// Создаем список Customers
        List<Customer> customerList = new ArrayList<>();
        // Добавляем несколько объектов Customer в список
        customerList.add(new Customer(1, "John Dol", "123-456-7890", "Description 1", 30, BigDecimal.valueOf(1000.00), true, new Date()));
        customerList.add(new Customer(2, "Jane Smith", "987-654-3210", "Description 2", 25, BigDecimal.valueOf(2000.00), false, new Date()));
        customerList.add(new Customer(3, "Bamb Smoot", "987-654-0000", "Description 3", 27, BigDecimal.valueOf(5500.00), true, new Date()));
        
        //создаем XML
        JDOMCreater creater = new JDOMCreater();
        Document doc = creater.createXMLDocument(customerList);
        
        //вывод в формате XML и отправки в строку, файл и т.д.
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());//Format.getPrettyFormat() - convert data in XML view mode
        
        //in String
        String xmlString = outputter.outputString(doc);
        System.out.println(xmlString);        
        
        File directory = createDirectory();
        
        //writing to a file
        try {
			FileWriter writer = new FileWriter(new File(directory + "/customers.xml"));
			outputter.output(doc, writer);
		} catch (IOException e) {
			System.out.println("Not file: " + e.getMessage());
		}
        
        //count records
        List<Element> list = doc.getRootElement().getChildren();
        System.out.println("Retrieved " + list.size());
       
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
