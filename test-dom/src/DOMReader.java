import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOMReader {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String NSURI = "http://www.example.org/customers";
	
	public List<Customer> getDataFromXML(String filename, String filter){
		List<Customer> data = new ArrayList<>();
		
		File xmlFile = new File(filename);
		Document doc = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();		
			doc = builder.parse(xmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}
		
//		Element root = doc.getDocumentElement();
//		System.out.println(root.getNodeName());
//		
//		NodeList nodes = root.getChildNodes();
//		for (int i = 0; i < nodes.getLength(); i++) {
//			Node child = nodes.item(i);
//			System.out.println(child.getNodeName());
//			System.out.println(child.getTextContent());
//		}		
						
		NodeList nodes = null;
		
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xPath = xFactory.newXPath();
		
		setNameSpace(xPath);//регистрируем namespace for filter
		
		if(!filter.isEmpty()) {
			try {
				XPathExpression exp = xPath.compile(filter);
				nodes = (NodeList) exp.evaluate(doc, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				System.out.println("XPath " + e.getMessage());
			}
		}else {
			nodes = doc.getElementsByTagNameNS(NSURI, "customer");//NS - пространство имен
		}
			
		System.out.println("Nodes found: " + nodes.getLength());
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Customer customer = new Customer();
			data.add(customer);
			
			Element xsiElement = (Element) nodes.item(i);
			String idAsString = xsiElement.getAttribute("xsi:"+Customer.ID);
			customer.setId(Integer.parseInt(idAsString));
			
			String content = getTextFromElement(xsiElement, Customer.NAME);
			customer.setName(content);			
			String phone = getTextFromElement(xsiElement, Customer.PHONE);
			customer.setPhone(phone);			
			String about = getTextFromElement(xsiElement, Customer.ABOUT);
			customer.setAbout(about);			
			String age = getTextFromElement(xsiElement, Customer.AGE);
			customer.setAge(Integer.parseInt(age));
			String balance = getTextFromElement(xsiElement, Customer.BALANCE);
			customer.setBalance(new BigDecimal(balance));
			String active = getTextFromElement(xsiElement, Customer.ACTIVE);
			customer.setActive(Boolean.parseBoolean(active));
			String joined = getTextFromElement(xsiElement, Customer.JOINED);
			try {
				customer.setJoined(dateFormat.parse(joined));
			} catch (ParseException e) {
				System.out.println("Date format: " + e.getMessage());
			}
			
		}
		
		return data;
		
	}

	private void setNameSpace(XPath xPath) {
		// «арегистрируйте пространство имен с использованием префикса "xsi"
		xPath.setNamespaceContext(new NamespaceContext() {
		    @Override
		    public String getNamespaceURI(String prefix) {
		        if ("xsi".equals(prefix)) {
		            return "http://www.example.org/customers";
		        }
		        return null;
		    }

		    @Override
		    public String getPrefix(String namespaceURI) {
		        throw new UnsupportedOperationException("Not supported yet.");
		    }

		    @Override
		    public Iterator<String> getPrefixes(String namespaceURI) {
		        throw new UnsupportedOperationException("Not supported yet.");
		    }
		});
	}

	private String getTextFromElement(Element xsiElement, String elementName) {
		Element node = (Element) xsiElement.getElementsByTagNameNS(NSURI, elementName).item(0);//NS
		String content = node.getTextContent();
		return content;
	}

}
