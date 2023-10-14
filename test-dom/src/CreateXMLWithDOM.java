import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreateXMLWithDOM {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		// ������� ������ Customers
        List<Customer> customerList = new ArrayList<>();
        // ��������� ��������� �������� Customer � ������
        customerList.add(new Customer(1, "John Dol", "123-456-7890", "Description 1", 30, BigDecimal.valueOf(1000.00), true, new Date()));
        customerList.add(new Customer(2, "Jane Smith", "987-654-3210", "Description 2", 25, BigDecimal.valueOf(2000.00), false, new Date()));
			
		DOMCreater creater = new DOMCreater();
		Document doc = creater.createXMLDoc(customerList);
		
		outputToString(doc);
		outputAsFile(doc, "./output/customers.xml");		
		
//		outputConsole(doc);		

	}

	private static void outputToString(Document doc)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		//������� ������ DOMSource, ������� ������������ �������� ������ � ���� ������� DOM (Document). 
		//� ������ ������, doc - ��� ��� ������ Document, ������� �������� ��������� XML.
		DOMSource source = new DOMSource(doc);
		//������� ������ StringWriter, ������� ������������ ����� ����� �������� � ������, ���� ����� ���������� �����. 
		//���� ����� ����� �������������� ��� ������ ���������� ��������������.
		StringWriter writer = new StringWriter();
		//������� ������ StreamResult � �������������� StringWriter � �������� ��������� ������. 
		//��� ������� Transformer � ���, ���� ��������� ��������� ��������������.
		StreamResult result = new StreamResult(writer);
		
		Transformer transformer = getTransformer();//create transformer
		
		/*
		 * ���������� ����������� ��������������. 
		 * �� ����� �������� ������ �� DOMSource (source) � ���������� ��������� � StreamResult (result). 
		 * ����������, ��� ������, ����� XML-�������� ������������ � ������.
		 */
		transformer.transform(source, result);
		
		//�������� ��������� ������������� ���������� �� StringWriter. � ���� ������ � xmlString ���������� XML-�������� � ���� ������.
		String xmlString = writer.toString();
		System.out.println(xmlString);
	}
	
	private static void outputAsFile(Document doc, String filename) 
			throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		getTransformer().transform(source, result);
	}

	private static Transformer getTransformer()
			throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		/*
		 * ������� ������� TransformerFactory, ������� ��������� ��������� ������� Transformer. 
		 * ��� ������ �������� ��������� ������� �� �������, ������� ������ ������������ ��������� javax.xml.transform.
		 * TransformerFactory � ����� jre/lib/jaxp.properties ��� ������ ������� �� ���������.
		 */
		TransformerFactory factory = TransformerFactory.newInstance();
		//������� ������ Transformer �� �������. Transformer ������������ ��� �������������� ������ �� ����� ����� � ������. 
		//����� �� ����� �������������� ��� �������������� ������� DOM � ������ XML.
		Transformer transformer = factory.newTransformer();
		
		/*
		 * ����� INDENT ���������� � "yes", Transformer ����� ��������� �������������� ������� � �������� ����� � ����� XML,
		 * ����� ��������� �������� � ������� ��������� ��������� ����� ���������.
		 */		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// ������������� ���������� ��������, ������������ ��� ������� ������ ������� ��� �������������� ������ XML.
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
		return transformer;
	}
	
	private static void outputConsole(Document doc) {
		System.out.println(doc.toString());
		
		Node root = doc.getFirstChild();
		System.out.println(root.getNodeName());
		
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			System.out.println(child.getNodeName());
			System.out.println(child.getTextContent());
		}
	}

}
