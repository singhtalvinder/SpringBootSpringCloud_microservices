package com.singh.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
	
	@Id
	private long id;
	
	// 'from' is a sql key word so will not compile. So we rename fields for the table 
	// using the @column but in class use the word 'from' for this field.
	@Column(name="currency_from")
	private String from;

	@Column(name="currency_to")
	private String to;
	private BigDecimal conversionMultiple;
	private int port;
	
	public ExchangeValue() {
		
	}
	
	public ExchangeValue(long id, String from, String to, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}

	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	
	
	
	

}
