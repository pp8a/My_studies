package com.example.jdom.creater;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.example.customer.Customer;

public class JDOMCreater {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JDOMCreater() {
		// TODO Auto-generated constructor stub
	}
	
	public Document createXMLDocument(List<Customer> data) {
		// Создаем корневой элемент с пространством имен
		Namespace xsiNamespace = Namespace.getNamespace("xsi", "http://www.example.org/customers");
		Element root = new Element("customers", xsiNamespace);
		
		// Создаем документ и добавляем в него корневой элемент
		Document doc = new Document(root);//doc.addContent(root);
		
		for (Customer customer : data) {
			Element xsiElement = new Element(Customer.CUSTOMER, xsiNamespace);
			xsiElement.setAttribute(Customer.ID, String.valueOf(customer.getId()), xsiNamespace);
//			xsiElement.addContent(new Element("name", xsiNamespace+Customer.NAME).setText(customer.getName()));
			root.addContent(xsiElement);
			
			addChildElement(xsiElement, Customer.NAME, customer.getName(), xsiNamespace);
			addChildElement(xsiElement, Customer.PHONE, customer.getPhone(), xsiNamespace);
			addChildElement(xsiElement, Customer.AGE, Integer.toString((customer.getAge())), xsiNamespace);
			
			Element about = addChildElement(xsiElement, Customer.ABOUT, "", xsiNamespace);
			CDATA cdata = new CDATA(customer.getAbout());
			about.addContent(cdata);
			
			addChildElement(xsiElement, Customer.BALANCE, customer.getBalance().toString(), xsiNamespace);
			addChildElement(xsiElement, Customer.ACTIVE, Boolean.toString(customer.isActive()), xsiNamespace);
			addChildElement(xsiElement, Customer.JOINED, dateFormat.format(customer.getJoined()), xsiNamespace);
		}
		
		
		return doc;
		
	}
	
	private Element addChildElement(Element parent, String elementName, String textValue, Namespace xsiNamespace) {
		Element child = new Element(elementName, xsiNamespace);
		child.setText(textValue);
		parent.addContent(child);
		
		return child; //for handling CDATA
	}

	
}
