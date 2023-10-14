import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMCreater {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Document doc = null;
	
	public DOMCreater() {
		// TODO Auto-generated constructor stub
	}

	public Document createXMLDoc(List<Customer> list) throws ParserConfigurationException {
		//����� ��������� ����� ��������� ������� ����������. ������� ���������� ������������ ��� �������� ����� ���������� XML.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		/*
		 * ����� ��������� ����������� ����������, ������� ���������� ��������� �������� ������ ��������� XML. 
		 * ����� newDocumentBuilder() �������� ����������� ����� ������� ��� �������� �����������.
		 */
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����� ��������� ����� ������ �������� XML � �������������� ����� ���������� �����������.
		doc = builder.newDocument();
		
		//��������� ����� ������� � ������ "customers", ������� ����� �������� �������� ��������� XML-���������.
//		Element root = doc.createElement("customers");
		// ��������� ����� ������� � ������ "customers", ������� ����� �������� �������� ��������� XML-���������.
	    Element root = doc.createElementNS("http://www.example.org/customers", "xsi:customers"); // ��� �������� �������� � ��������� ������������ ����
	    root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.example.org/customers");
	    root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema-instance");
		//�������� ������� ����������� � �������� ��������� �������� � ����� ��������.
		doc.appendChild(root);
		
		for (Customer customer : list) {
			Element xsiElement = addElement(root, "xsi:customer", "");
			
			String idAsString = Integer.toString(customer.getId());
			xsiElement.setAttribute("xsi:"+Customer.ID, idAsString);
			
			addElement(xsiElement, "xsi:"+Customer.NAME, customer.getName());
			addElement(xsiElement, "xsi:"+Customer.PHONE, customer.getPhone());
			addElement(xsiElement, "xsi:"+Customer.AGE, Integer.toString(customer.getAge()));
			addElement(xsiElement, "xsi:"+Customer.BALANCE, customer.getBalance().toString());
			addElement(xsiElement, "xsi:"+Customer.ACTIVE, Boolean.toString(customer.isActive()));
			
			Element about = addElement(xsiElement, "xsi:"+Customer.ABOUT, "");
			CDATASection cdata = doc.createCDATASection(customer.getAbout());
			about.appendChild(cdata);			
			
			addElement(xsiElement, "xsi:"+Customer.JOINED, dateFormat.format(customer.getJoined()));
			
		}
		
		return doc;
	}

	private Element addElement(Element parent, String elementName, String textValue) {
		Element childElement = doc.createElement(elementName);
		childElement.setTextContent(textValue);
		parent.appendChild(childElement);
		return childElement;
	}
	
	
}
