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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.example.customer.Customer;

public class StAXEventReader {
private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<Customer> getDataFromXML(String filename) throws FileNotFoundException, XMLStreamException{
		List<Customer> data = new ArrayList<>();
		Customer customer = null;
		
		InputStream in =  new FileInputStream(new File(filename));
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(in);
		
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
//			System.out.println(XMLStreamUtils.getEventTypeName(eventType));
			
			if(event.isStartElement()) {
				
				StartElement startElement = event.asStartElement();				
				String elementName = startElement.getName().getLocalPart();
                String namespaceURI = startElement.getName().getNamespaceURI();                
				
				switch (elementName) {
				case Customer.CUSTOMER:
					customer = new Customer();
					data.add(customer);
					QName qName = new QName(namespaceURI, Customer.ID);
					String idAsString = startElement.getAttributeByName(qName).getValue();
					customer.setId(Integer.parseInt(idAsString));
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
