package com.example.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date>{

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return dateFormat.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		// TODO Auto-generated method stub
		return dateFormat.format(v);
	}

}
