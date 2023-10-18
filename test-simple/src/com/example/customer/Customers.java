package com.example.customer;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(reference = "http://example.com/customers", prefix = "xsi")
public class Customers {
	@ElementList(inline=true, entry = "customer")
	private List<Customer> customers;
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
