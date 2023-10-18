package com.example.customer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "customer")
@Namespace(reference = "http://example.com/customers", prefix = "xsi")
public class Customer {
	@Attribute
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private int id;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private String name;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private String phone;
	// огда data установлен в true, это говорит библиотеке, что поле должно быть представлено как содержимое XML-элемента, 
	//а не как атрибут или структурный элемент. 
	@Element(data=true)	
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private String about;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private int age;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private BigDecimal balance;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private boolean active;
	@Element
	@Namespace(reference = "http://example.com/customers", prefix = "xsi")
	private Date joined;
	
	//data items names
	public static final String 
		ID = "id",
		NAME = "name",
		PHONE = "phone",
		ABOUT = "about",
		AGE = "age",
		BALANCE = "balance",
		ACTIVE = "active",
		JOINED = "joined",
		CUSTOMER = "customer";
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Customer(int id, String name, String phone, String about, int age, BigDecimal balance, boolean active,
			Date joined) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.about = about;
		this.age = age;
		this.balance = balance;
		this.active = active;
		this.joined = joined;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}



	@Override
	public String toString() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", about=" + about + ", age=" + age
				+ ", balance=" + balance + ", active=" + active + ", joined=" + dateFormat.format(joined) + "]";
	}

}
