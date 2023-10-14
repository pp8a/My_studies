import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String currentDirectory = System.getProperty("user.dir");		
		String filename = currentDirectory + "/src/customer.xml";
		
		SAXCustomerHandler saxHandler = new SAXCustomerHandler();
		List<Customer> data = saxHandler.readDataFromXML(filename);
		
		System.out.println("Number customers " + data.size());
		
		for (Customer customer : data) {
//			System.out.println("Customer " + customer.getId());
			System.out.println(customer);
		}

	}

}
