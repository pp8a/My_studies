import java.util.List;

public class ReadXMLWithDOM {

	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");		
		String filename = currentDirectory + "/output/customers.xml";
		
		//Simple reading
		DOMReader reader = new DOMReader();
		List<Customer> data = reader.getDataFromXML(filename, "");
		
		System.out.println("There are " + data.size() + " records");		
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
		
		//reading with filter
		String filter = "//xsi:customer[xsi:age>25]";
		//XPath
		data = reader.getDataFromXML(filename, filter);
		
		System.out.println("There are " + data.size() + " records");		
		
		for (Customer customer : data) {
			System.out.println(customer);
		}

	}

}
