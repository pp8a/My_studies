package com.example.customer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @XmlRootElement, это указывает JAXB, что экземпляры этого класса могут быть преобразованы в XML элементы верхнего уровня. 
 * Такой элемент XML будет содержать данные, соответствующие полям этого класса.
 */
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "phone", "about", "age", "balance", "active", "joined"})
public class Customer {
	private int id;
	private String name;
	private String phone;
	private String about;
	private int age;
	private BigDecimal balance;
	private boolean active;
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


	@XmlAttribute(name="id", namespace = "http://example.com/customers")
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

	@XmlJavaTypeAdapter(DateAdapter.class)
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
