import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXCustomerHandler extends DefaultHandler{
	
	private List<Customer> data;
	private Customer customer;
	private String currentElement = "";
	
	private StringBuilder currentText;
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<Customer> readDataFromXML(String filename) throws ParserConfigurationException, IOException{
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);//url
			SAXParser saxParser = factory.newSAXParser();
			
			saxParser.parse(new File(filename), this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("SAX error detail: " + e.getMessage());
		}		
		
		return data;
	}

	@Override
	public void startDocument() throws SAXException {
//		System.out.println("Start Document");
		data = new ArrayList<>();
	}
	
	@Override
	public void endDocument() throws SAXException {
//		System.out.println("End Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		System.out.println("Start element " + qName);
//		if(!uri.isEmpty()) {
//			System.out.println("uri: " + uri);
//		}
		currentElement = localName;
		
		switch (currentElement) {
		case "customers":	
			String customersNamespaceURI = uri;
	        System.out.println("Namespace URI for <customers>: " + customersNamespaceURI);
			break;
		case "customer":
			if(uri.equals("http://www.example.org/customers")) {
				customer = new Customer();
				String idAsString = attributes.getValue(Customer.ID);
				
				if (idAsString != null) {
	                try {
	                    int customerId = Integer.parseInt(idAsString);
	                    customer.setId(customerId);
	                } catch (NumberFormatException e) {
	                    System.out.println("NumberFormatException " + e.getLocalizedMessage());
	                }
	            }
				
				data.add(customer);
			}
			
			break;

		default:
			currentText = new StringBuilder();
			
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		System.out.println("End element " + qName);
		
		if(currentElement.equals("customers") || currentElement.equals("customer")
				|| !uri.equals("http://www.example.org/customers")) {
			return;
		}
		
		String content = currentText.toString();
		
		switch (currentElement) {
		case Customer.NAME:
			customer.setName(content);
			break;
		case Customer.PHONE:
			customer.setPhone(content);
			break;
		case Customer.ABOUT:
			customer.setAbout(content);
			break;
		case Customer.AGE:
			customer.setAge(Integer.parseInt(content));
			break;
		case Customer.ACTIVE:
			customer.setActive(Boolean.parseBoolean(content));
			break;
		case Customer.BALANCE:
			customer.setBalance(new BigDecimal(content));
			break;
		case Customer.JOINED:
			try {
				customer.setJoined(dateFormat.parse(content));
			} catch (ParseException e) {
				System.out.println("Data format: " + e.getLocalizedMessage());
			}
			break;

		default:
			break;
		}
		currentElement = "";
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(currentText != null) {
			currentText.append(ch, start, length);
		}
		
//		String content = new String(ch, start, length).trim();
//		if(!content.isEmpty()) {
//			System.out.println("Text: " + content);
//		}
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning!!!");
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("Error!");
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("Fatal error.");
	}
}
