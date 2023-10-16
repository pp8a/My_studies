package com.example.customer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

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

//	@Override
//	public String toString() {
//		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG);
//		
//		return "Customer [id=" + this.id + ", name=" + this.name + ", joined: " + format.format(this.joined) + "]";
//	}
	
	
}
