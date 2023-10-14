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
		//Здесь создается новый экземпляр фабрики документов. Фабрика документов используется для создания новых документов XML.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		/*
		 * Здесь создается построитель документов, который фактически выполняет создание нового документа XML. 
		 * Метод newDocumentBuilder() вызывает статический метод фабрики для создания построителя.
		 */
		DocumentBuilder builder = factory.newDocumentBuilder();
		//Здесь создается новый пустой документ XML с использованием ранее созданного построителя.
		doc = builder.newDocument();
		
		//Создается новый элемент с именем "customers", который будет являться корневым элементом XML-документа.
//		Element root = doc.createElement("customers");
		// Создается новый элемент с именем "customers", который будет являться корневым элементом XML-документа.
	    Element root = doc.createElementNS("http://www.example.org/customers", "xsi:customers"); // для создания элемента с указанием пространства имен
	    root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.example.org/customers");
	    root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema-instance");
		//Корневой элемент добавляется в качестве дочернего элемента в новый документ.
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
