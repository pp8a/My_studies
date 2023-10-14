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
		// Создаем список Customers
        List<Customer> customerList = new ArrayList<>();
        // Добавляем несколько объектов Customer в список
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
		//Создает объект DOMSource, который представляет исходные данные в виде объекта DOM (Document). 
		//В данном случае, doc - это ваш объект Document, который содержит структуру XML.
		DOMSource source = new DOMSource(doc);
		//Создает объект StringWriter, который представляет собой поток символов в памяти, куда можно записывать текст. 
		//Этот поток будет использоваться для записи результата преобразования.
		StringWriter writer = new StringWriter();
		//Создает объект StreamResult с использованием StringWriter в качестве выходного потока. 
		//Это говорит Transformer о том, куда поместить результат преобразования.
		StreamResult result = new StreamResult(writer);
		
		Transformer transformer = getTransformer();//create transformer
		
		/*
		 * Производит фактическое преобразование. 
		 * Он берет исходные данные из DOMSource (source) и записывает результат в StreamResult (result). 
		 * Фактически, это момент, когда XML-документ превращается в строку.
		 */
		transformer.transform(source, result);
		
		//Получает строковое представление результата из StringWriter. В этот момент в xmlString содержится XML-документ в виде строки.
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
		 * Создает фабрику TransformerFactory, которая позволяет создавать объекты Transformer. 
		 * Эта строка получает экземпляр фабрики из системы, которая обычно определяется свойством javax.xml.transform.
		 * TransformerFactory в файле jre/lib/jaxp.properties или другим образом по умолчанию.
		 */
		TransformerFactory factory = TransformerFactory.newInstance();
		//Создает объект Transformer из фабрики. Transformer используется для преобразования данных из одной формы в другую. 
		//Здесь он будет использоваться для преобразования объекта DOM в строку XML.
		Transformer transformer = factory.newTransformer();
		
		/*
		 * Когда INDENT установлен в "yes", Transformer будет вставлять дополнительные пробелы и переводы строк в вывод XML,
		 * чтобы выровнять элементы и сделать структуру документа более наглядной.
		 */		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// устанавливает количество пробелов, используемых для каждого уровня отступа при форматировании вывода XML.
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
