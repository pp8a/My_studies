package com.example.stax.creater;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.example.customer.Customer;

import javanet.staxutils.IndentingXMLStreamWriter;

public class StAXStreamCreater {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void createDocument(List<Customer> data, String filename) {		
		
//		StringWriter strWriter = new StringWriter();	
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(filename), StandardCharsets.UTF_8);//1
		} catch (IOException e) {
			System.out.println("File: " + e.getMessage());
		}
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();//2
		
		String namespaceURI = "http://www.example.org/customers";
		String prefix = "xsi";
		
		try {
			XMLStreamWriter	streamWriter = factory.createXMLStreamWriter(fileWriter);//strWriter	3	
			
			IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(streamWriter);//for view XML
				
//			writer.writeStartDocument();
			writer.writeStartDocument("UTF-8", "1.0");
			
			writer.writeStartElement(prefix, "customers", namespaceURI);
			writer.writeNamespace(prefix, namespaceURI);			
			
			for (Customer customer : data) {
				createXsiElement(writer, customer, namespaceURI);
			}			
			
			writer.writeEndElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
			
			
		} catch (XMLStreamException e) {
			System.out.println("XML Stream writer: " + e.getMessage());
		}
		
//		System.out.println(strWriter.toString());
	}

	private void createXsiElement(XMLStreamWriter writer, Customer customer, String namespaceURI) {
		try {
			writer.writeStartElement(namespaceURI, Customer.CUSTOMER);
			writer.writeAttribute(namespaceURI, Customer.ID, Integer.toString(customer.getId()));
			
			writeDataElement(writer, customer.getName(), Customer.NAME, namespaceURI);
			writeDataElement(writer, customer.getPhone(), Customer.PHONE, namespaceURI);
			writeDataElement(writer, Integer.toString(customer.getAge()), Customer.AGE, namespaceURI);
			writeDataElement(writer, customer.getAbout(), Customer.ABOUT, namespaceURI);
			writeDataElement(writer, customer.getBalance().toString(), Customer.BALANCE, namespaceURI);
			writeDataElement(writer, Boolean.toString(customer.isActive()), Customer.ACTIVE, namespaceURI);			
			writeDataElement(writer, dateFormat.format(customer.getJoined()), Customer.JOINED, namespaceURI);
			
			writer.writeEndElement();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void writeDataElement(XMLStreamWriter writer, String value, String elementName, String namespaceURI) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, elementName);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

}
