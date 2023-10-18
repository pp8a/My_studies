package com.example.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.transform.Transform;

public class DateTransform implements  Transform<Date>{
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
    public Date read(String value) throws Exception {  
            return dateFormat.parse(value);       
    }

    @Override
    public String write(Date value) throws Exception {
        return dateFormat.format(value);
    }

}
