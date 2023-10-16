package com.example.stax.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import com.example.customer.Customer;

import javanet.staxutils.XMLStreamUtils;

public class StAXStreamReader {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<Customer> getDataFromXML(String filename) throws FileNotFoundException, XMLStreamException{
		List<Customer> data = new ArrayList<>();
		Customer customer = null;
		
		InputStream in =  new FileInputStream(new File(filename));
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(in);
		
		int eventType = reader.next();//START ELEMENT
		
		while (reader.hasNext()) {
			eventType = reader.next();
//			System.out.println(XMLStreamUtils.getEventTypeName(eventType));
			
			if(eventType == XMLEvent.START_ELEMENT) {
				
				String elementName = reader.getLocalName();
                String namespaceURI = reader.getNamespaceURI();
				
				switch (elementName) {
				case Customer.CUSTOMER:
					customer = new Customer();
					data.add(customer);
					customer.setId(Integer.parseInt(reader.getAttributeValue(namespaceURI, Customer.ID)));
					break;
				case Customer.NAME:
					customer.setName(reader.getElementText());
					break;
				case Customer.PHONE:
					customer.setPhone(reader.getElementText());
					break;
				case Customer.AGE:
					customer.setAge(Integer.parseInt(reader.getElementText()));
					break;
				case Customer.ABOUT:
					customer.setAbout(reader.getElementText());
					break;
				case Customer.BALANCE:
					customer.setBalance(new BigDecimal(reader.getElementText()));
					break;
				case Customer.ACTIVE:
					customer.setActive(Boolean.parseBoolean(reader.getElementText()));
					break;
				case Customer.JOINED:
					try {
						customer.setJoined(dateFormat.parse(reader.getElementText()));
					} catch (ParseException | XMLStreamException e) {
						System.out.println("Data format: " + e.getMessage());
					}
					break;
				default:
					break;
				}
			}
			
		}
		
		return data;
	}
	

}
