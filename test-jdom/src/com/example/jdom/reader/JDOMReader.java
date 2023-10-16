package com.example.jdom.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.example.customer.Customer;

public class JDOMReader {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Namespace xsiNamespace = Namespace.getNamespace("xsi", "http://www.example.org/customers");
	
	public List<Customer> getDataFromXML(String filename, String filter){
		List<Customer> data = new ArrayList<>();
		
		File file = new File(filename);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		
		try {
			doc = builder.build(file);
		} catch (JDOMException | IOException e) {
			System.out.println("JDOM: " + e.getMessage());
			return null;
		}
		
		List<Element> xsiElements;
		
		if(filter.isEmpty()) {
			//получаем из doc
			Element root = doc.getRootElement();
			Namespace namespace = root.getNamespace();		
			xsiElements = root.getChildren(Customer.CUSTOMER, namespace);
		}else {
			XPathFactory xfactory = XPathFactory.instance();			
			XPathExpression<Element> exp = xfactory.compile(filter, Filters.element(), null, xsiNamespace);
			xsiElements = exp.evaluate(doc);
		}
		
		for (Element element : xsiElements) {
			Namespace customerNamespace = element.getNamespace();
			
			Customer customer = new Customer();
			data.add(customer);
			
			Attribute attribute = element.getAttribute(Customer.ID, customerNamespace);
			try {
				customer.setId(attribute.getIntValue());
			} catch (DataConversionException e) {
				System.out.println("Attribute: " + e.getMessage());
			}
			
			customer.setName(element.getChildText(Customer.NAME, customerNamespace));
			customer.setPhone(element.getChildText(Customer.PHONE, customerNamespace));
			customer.setAbout(element.getChildText(Customer.ABOUT, customerNamespace));
			customer.setAge(Integer.parseInt(element.getChildText(Customer.AGE, customerNamespace)));
			customer.setBalance(new BigDecimal(element.getChildText(Customer.BALANCE, customerNamespace)));
			customer.setActive(Boolean.parseBoolean(element.getChildText(Customer.ACTIVE, customerNamespace)));
			
			try {
				customer.setJoined(dateFormat.parse(element.getChildText(Customer.JOINED, customerNamespace)));
			} catch (ParseException e) {
				System.out.println("Data format: " + e.getMessage());
			}
						
		}
		
		//вывод в формате XML
//        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());//Format.getPrettyFormat() - convert data in XML view mode
//        String xmlString = outputter.outputString(doc);
//        System.out.println(xmlString);
		
		return data;
	}
	

}
